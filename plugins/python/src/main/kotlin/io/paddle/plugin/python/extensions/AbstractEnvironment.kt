package io.paddle.plugin.python.extensions

import io.paddle.execution.ExecutionResult
import io.paddle.plugin.python.dependencies.VenvDir
import io.paddle.plugin.python.dependencies.packages.PyPackage
import io.paddle.plugin.python.dependencies.resolvers.PipResolver
import io.paddle.project.Project
import io.paddle.terminal.Terminal
import java.nio.file.Path
import kotlin.io.path.absolutePathString

abstract class AbstractEnvironment(val project: Project, val venv: VenvDir) {

    val localInterpreterPath: Path
        get() = venv.getInterpreterPath(project)

    protected abstract val initInterpreterPath: Path

    abstract fun install(pkg: PyPackage)

    fun initialize(): ExecutionResult {
        return project.executor.execute(
            initInterpreterPath.toString(),
            listOf("-m", "venv", "--clear", venv.absolutePath),
            project.workDir,
            project.terminal
        ).then {
            project.executor.execute(
                localInterpreterPath.absolutePathString(),
                listOf("-m", "pip", "install", PipResolver.PIP_RESOLVER_URL),
                project.workDir,
                Terminal.MOCK
            )
        }
    }

    fun runModule(module: String, arguments: List<String> = emptyList()): ExecutionResult {
        return project.executor.execute(
            localInterpreterPath.absolutePathString(),
            listOf("-m", module, *arguments.toTypedArray()),
            project.workDir,
            project.terminal
        )
    }

    fun runScript(file: String, arguments: List<String> = emptyList()): ExecutionResult {
        return project.executor.execute(
            localInterpreterPath.absolutePathString(),
            listOf(file, *arguments.toTypedArray()),
            project.workDir,
            project.terminal
        )
    }
}
