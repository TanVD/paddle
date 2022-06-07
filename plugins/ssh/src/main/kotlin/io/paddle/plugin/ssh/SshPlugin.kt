package io.paddle.plugin.ssh

import io.paddle.plugin.Plugin
import io.paddle.project.Project
import io.paddle.specification.ConfigSpecView
import io.paddle.specification.tree.CompositeSpecTreeNode
import io.paddle.specification.tree.StringSpecTreeNode
import io.paddle.tasks.Task
import io.paddle.terminal.Terminal
import io.paddle.utils.config.PluginsConfig

@Suppress("unused")
object SshPlugin : Plugin {
    private fun isSshExecutorSelectedIn(project: Project): Boolean {
        return project.config.get<String>("executor.type") == "ssh"
    }

    override fun configure(project: Project) {
        val plugins = object : PluginsConfig(project) {
            val embedded by plugins<String>("embedded")
        }
        val executors = object : ConfigSpecView("executor", project.configSpec) {
            val available by string("type")
        }
        if (plugins.embedded.contains("ssh")) {
            executors.available.validValues = executors.available.validValues ?: mutableListOf()
            executors.available.validValues!!.add("ssh")
        }
        if (isSshExecutorSelectedIn(project)) {
            project.configSpec.get<CompositeSpecTreeNode>("executor")?.run {
                namesOfRequired = (namesOfRequired ?: mutableListOf()).apply { addAll(listOf("user", "host", "directory")) }
                children["user"] = StringSpecTreeNode(description = "User to login via ssh")
                children["host"] = StringSpecTreeNode(description = "Host to connect via ssh")
                children["directory"] = StringSpecTreeNode(description = "Absolute path of remote working directory")
            }
        }

        val executor = project.extensions.get(SshCommandExecutor.Extension.key) ?: return
        project.executor = executor
        project.terminal.stdout("> Executor :ssh: ${Terminal.colored("ENABLED", Terminal.Color.CYAN)}")
    }

    override fun tasks(project: Project): List<Task> {
        return emptyList()
    }

    @Suppress("UNCHECKED_CAST")
    override fun extensions(project: Project): List<Project.Extension<Any>> {
        val isSshConfigured = isSshExecutorSelectedIn(project).and(project.config.get<String>("executor.user") != null)
            .and(project.config.get<String>("executor.host") != null).and(project.config.get<String>("executor.directory") != null)
        return if (isSshConfigured) {
            listOf(SshCommandExecutor.Extension) as List<Project.Extension<Any>>
        } else emptyList()
    }
}
