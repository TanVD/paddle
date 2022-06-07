package io.paddle.plugin.docker

import io.paddle.plugin.Plugin
import io.paddle.project.Project
import io.paddle.specification.ConfigSpecView
import io.paddle.specification.tree.CompositeSpecTreeNode
import io.paddle.specification.tree.StringSpecTreeNode
import io.paddle.tasks.Task
import io.paddle.terminal.Terminal
import io.paddle.utils.config.PluginsConfig

@Suppress("unused")
object DockerPlugin : Plugin {
    private fun isDockerExecutorSelectedIn(project: Project): Boolean {
        return project.config.get<String>("executor.type") == "docker"
    }

    override fun configure(project: Project) {
        val plugins = object : PluginsConfig(project) {
            val embedded by plugins<String>("embedded")
        }
        val executors = object : ConfigSpecView("executor", project.configSpec) {
            val available by string("type")
        }
        if (plugins.embedded.contains("docker")) {
            executors.available.validValues = executors.available.validValues ?: mutableListOf()
            executors.available.validValues!!.add("docker")
        }
        if (isDockerExecutorSelectedIn(project)) {
            project.configSpec.get<CompositeSpecTreeNode>("executor")?.run {
                namesOfRequired = (namesOfRequired ?: mutableListOf()).apply { add("image") }
                children["image"] = StringSpecTreeNode(description = "Image to be used for build")
            }
        }
        val executor = project.extensions.get(DockerCommandExecutor.Extension.key) ?: return
        project.executor = executor
        project.terminal.stdout("> Executor :docker: ${Terminal.colored("ENABLED", Terminal.Color.CYAN)}")
    }

    override fun tasks(project: Project): List<Task> {
        return emptyList()
    }

    @Suppress("UNCHECKED_CAST")
    override fun extensions(project: Project): List<Project.Extension<Any>> {
        return if (isDockerExecutorSelectedIn(project).and(project.config.get<String>("executor.image") != null)) {
            listOf(DockerCommandExecutor.Extension) as List<Project.Extension<Any>>
        } else emptyList()
    }
}
