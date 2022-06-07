package io.paddle.plugin.pyinjector.dependencies

import io.paddle.plugin.pyinjector.extensions.PluginsVenvDir
import io.paddle.plugin.pyinjector.extensions.pyPluginsEnvironment
import io.paddle.plugin.python.PaddlePyConfig
import io.paddle.plugin.python.dependencies.AbstractTempVenvManager
import io.paddle.plugin.python.dependencies.resolvers.PipResolver
import io.paddle.project.Project
import io.paddle.terminal.Terminal
import java.nio.file.Path
import kotlin.io.path.absolutePathString

class PluginsTempVenvManager(venvDir: PluginsVenvDir, project: Project) : AbstractTempVenvManager(venvDir, project) {
    companion object {
        fun create(project: Project): PluginsTempVenvManager {
            val venv = PluginsVenvDir(PaddlePyConfig.pluginsVenvs.resolve(project.id).toFile())

            project.executor.execute(
                command = project.pyPluginsEnvironment.localInterpreterPath.absolutePathString(),
                args = listOf("-m", "venv") + PaddlePyConfig.pluginsVenvs.resolve(project.id).toString(),
                workingDir = PaddlePyConfig.paddleHome.toFile(),
                terminal = Terminal.MOCK,
                verbose = true
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
            }.orElse { error("Failed to create Paddle's internal virtualenv. Check your python installation.") }

            return PluginsTempVenvManager(venv, project)
        }
    }
}
