package io.paddle.plugin.python.tasks.venv

import io.paddle.plugin.python.PyDefaultVersions
import io.paddle.plugin.python.extensions.*
import io.paddle.plugin.python.tasks.PythonPluginTaskGroups
import io.paddle.plugin.standard.tasks.clean
import io.paddle.project.PaddleProject
import io.paddle.tasks.Task
import io.paddle.tasks.incremental.IncrementalTask
import io.paddle.utils.hash.Hashable
import io.paddle.utils.hash.hashable
import kotlin.system.measureTimeMillis

class VenvTask(project: PaddleProject) : IncrementalTask(project) {
    override val id: String = "venv"

    override val group: String = PythonPluginTaskGroups.VENV

    override val inputs: List<Hashable>
        get() = listOf(project.globalInterpreter)
    override val outputs: List<Hashable>
        get() = listOf(project.environment.venv.resolve("pyvenv.cfg").hashable())

    override val dependencies: List<Task>
        get() = listOf(project.tasks.getOrFail("resolveInterpreter")) + project.subprojects.getAllTasksById(this.id)

    override fun initialize() {
        project.requirements.descriptors.add(
            Requirements.Descriptor(
                name = "wheel",
                versionSpecifier = PyDefaultVersions.WHEEL,
                type = Requirements.Descriptor.Type.DEV
            )
        )
        project.tasks.clean.locations.add(project.environment.venv)
    }

    override fun act() {
        project.terminal.info("Creating virtual environment...")
        val duration = measureTimeMillis {
            project.environment.initialize().orElse {
                throw ActException("virtualenv creation has failed with code: $it")
            }
        }
        project.terminal.info("Finished: ${duration}ms")
    }
}
