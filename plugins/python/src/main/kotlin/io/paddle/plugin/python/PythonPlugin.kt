package io.paddle.plugin.python

import io.paddle.plugin.Plugin
import io.paddle.plugin.python.extensions.*
import io.paddle.plugin.python.tasks.env.*
import io.paddle.plugin.python.tasks.exec.RunTask
import io.paddle.plugin.python.tasks.linter.MyPyTask
import io.paddle.plugin.python.tasks.linter.PyLintTask
import io.paddle.plugin.python.tasks.migrate.ParseRequirementsTxtTask
import io.paddle.plugin.python.tasks.resolve.*
import io.paddle.plugin.python.tasks.tests.PyTestTask
import io.paddle.plugin.standard.tasks.CleanTask
import io.paddle.project.Project
import io.paddle.specification.tree.*
import io.paddle.tasks.Task
import io.paddle.utils.config.PluginsConfig

@Suppress("unused")
object PythonPlugin : Plugin {
    override fun configure(project: Project) {
        val plugins = object : PluginsConfig(project) {
            val embedded by plugins<String>("embedded")
        }
        if (plugins.embedded.contains("python")) {
            project.configSpec.root.children["environment"] =
                CompositeSpecTreeNode(
                    description = "Environment that should be used by Paddle for Python build process",
                    namesOfRequired = mutableListOf("path", "python"),
                    children = mutableMapOf(
                        "path" to StringSpecTreeNode(description = "Path to the virtual environment location"),
                        "python" to StringSpecTreeNode(description = "Version of Python interpreter to be used")
                    )
                )

            project.configSpec.root.children["repositories"] =
                ArraySpecTreeNode(
                    description = "List of the available PyPI repositories",
                    items = CompositeSpecTreeNode(
                        namesOfRequired = mutableListOf("name", "url"),
                        children = mutableMapOf(
                            "name" to StringSpecTreeNode(), "url" to StringSpecTreeNode(),
                            "default" to BooleanSpecTreeNode(), "secondary" to BooleanSpecTreeNode()
                        )
                    )
                )

            project.configSpec.root.children["requirements"] =
                ArraySpecTreeNode(
                    description = "List of project requirements",
                    items = CompositeSpecTreeNode(
                        namesOfRequired = mutableListOf("name"),
                        children = mutableMapOf(
                            "name" to StringSpecTreeNode(),
                            "version" to StringSpecTreeNode(),
                            "repository" to StringSpecTreeNode()
                        )
                    )
                )
        }
    }

    override fun tasks(project: Project): List<Task> {
        return listOf(
            CleanTask(project),
            VenvTask(project),
            InstallTask(project),
            ResolveInterpreterTask(project),
            ResolveRequirementsTask(project),
            ResolveRepositoriesTask(project),
            LockTask(project),
            CiTask(project),
            MyPyTask(project),
            PyLintTask(project),
            PyTestTask(project),
            ParseRequirementsTxtTask(project)
        ) + RunTask.from(project)
    }

    @Suppress("UNCHECKED_CAST")
    override fun extensions(project: Project): List<Project.Extension<Any>> {
        return listOf(
            Requirements.Extension,
            Repositories.Extension,
            Environment.Extension,
            Interpreter.Extension
        ) as List<Project.Extension<Any>>
    }
}
