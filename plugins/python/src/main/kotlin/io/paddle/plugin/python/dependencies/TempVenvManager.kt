package io.paddle.plugin.python.dependencies

import io.paddle.execution.ExecutionResult
import io.paddle.plugin.python.PaddlePyConfig
import io.paddle.plugin.python.dependencies.packages.CachedPyPackage.Companion.PYPACKAGE_CACHE_FILENAME
import io.paddle.plugin.python.dependencies.packages.IResolvedPyPackage
import io.paddle.plugin.python.dependencies.packages.PyPackage
import io.paddle.plugin.python.dependencies.resolvers.PipResolver
import io.paddle.plugin.python.extensions.environment
import io.paddle.plugin.python.utils.jsonParser
import io.paddle.project.Project
import io.paddle.terminal.Terminal
import kotlinx.serialization.encodeToString
import java.io.File
import java.nio.file.Path
import kotlin.io.path.absolutePathString


abstract class AbstractTempVenvManager(val venv: VenvDir, val project: Project) {
    private val interpreterPath: Path
        get() = venv.getInterpreterPath(project)

    fun install(pkg: PyPackage): ExecutionResult {
        return project.executor.execute(
            command = interpreterPath.absolutePathString(),
            args = listOf("-m", "pip", "install", "--no-deps", pkg.distributionUrl),
            workingDir = PaddlePyConfig.paddleHome.toFile(),
            terminal = project.terminal
        ).also {
            val infoDir = InstalledPackageInfoDir.findByNameAndVersion(venv.sitePackages, pkg.name, pkg.version)
            infoDir.addFile(PYPACKAGE_CACHE_FILENAME, jsonParser.encodeToString(pkg))
        }
    }

    fun uninstall(pkg: IResolvedPyPackage): ExecutionResult {
        return project.executor.execute(
            command = interpreterPath.absolutePathString(),
            args = listOf("-m", "pip", "uninstall", "-y", pkg.name),
            workingDir = PaddlePyConfig.paddleHome.toFile(),
            terminal = Terminal.MOCK
        )
    }

    fun getFilesRelatedToPackage(pkg: IResolvedPyPackage): List<File> {
        return InstalledPackageInfoDir.findByNameAndVersion(venv.sitePackages, pkg.name, pkg.version).files
    }
}

/**
 * A service for managing Paddle's internal virtual environment, where all the packages are installed for the first time.
 * Then, Paddle moves them to the corresponding ~/.paddle/cache/repo/package/version folder.
 */
class TempVenvManager private constructor(venv: VenvDir, project: Project) : AbstractTempVenvManager(venv, project) {
    companion object {
        @Volatile
        private var instance: TempVenvManager? = null

        fun getInstance(project: Project): TempVenvManager =
            instance ?: synchronized(this) {
                instance ?: getTempVenvManager(project).also { instance = it }
            }

        private fun getTempVenvManager(project: Project): TempVenvManager {
            val venv = VenvDir(PaddlePyConfig.venvsDir.resolve(project.id).toFile())
            createTempVenv(project, venv).orElse { error("Failed to create Paddle's internal virtualenv. Check your python installation.") }
            return TempVenvManager(venv, project)
        }

        private fun createTempVenv(project: Project, venv: VenvDir, options: List<String> = emptyList(), verbose: Boolean = true): ExecutionResult {
            return project.executor.execute(
                command = project.environment.localInterpreterPath.absolutePathString(),
                args = listOf("-m", "venv") + options + PaddlePyConfig.venvsDir.resolve(project.id).toString(),
                workingDir = PaddlePyConfig.paddleHome.toFile(),
                terminal = Terminal.MOCK,
                verbose = verbose
            ).then {
                project.executor.execute(
                    command = venv.getInterpreterPath(project).absolutePathString(),
                    args = listOf("-m", "pip", "install", PipResolver.PIP_RESOLVER_URL),
                    workingDir = project.workDir,
                    terminal = Terminal.MOCK
                )
            }.then {
                project.executor.execute(
                    command = venv.getInterpreterPath(project).absolutePathString(),
                    args = listOf("-m", "pip", "install", "--upgrade", "pip"),
                    workingDir = project.workDir,
                    terminal = Terminal.MOCK
                )
            }
        }
    }
}
