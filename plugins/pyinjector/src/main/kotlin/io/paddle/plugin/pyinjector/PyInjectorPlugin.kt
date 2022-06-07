package io.paddle.plugin.pyinjector

import io.paddle.plugin.Plugin
import io.paddle.plugin.pyinjector.extensions.*
import io.paddle.plugin.pyinjector.interop.PyPlugin
import io.paddle.project.Project
import io.paddle.specification.ConfigSpecView
import io.paddle.specification.tree.*
import io.paddle.tasks.Task

@Suppress("unused")
object PyInjectorPlugin : Plugin {
    override fun configure(project: Project) {
        val configSpec = object : ConfigSpecView("plugins", project.configSpec) {
            val repos by list<CompositeSpecTreeNode>("repositories")
        }

        val repoTypes = configSpec.repos.items
        // TODO: remove this and add to inspections
        repoTypes.validSpecs = repoTypes.validSpecs ?: mutableListOf()
        repoTypes.validSpecs!!.add(
            CompositeSpecTreeNode(
                description = "Python packages repository", namesOfRequired = mutableListOf("name", "url"),
                children = mutableMapOf(
                    "name" to StringSpecTreeNode(),
                    "url" to StringSpecTreeNode(),
                    "secondary" to BooleanSpecTreeNode(),
                    "default" to BooleanSpecTreeNode()
                )
            )
        )
        repoTypes.validSpecs!!.add(
            CompositeSpecTreeNode(
                description = "Local Python modules repository", namesOfRequired = mutableListOf("name", "dir"),
                children = mutableMapOf("name" to StringSpecTreeNode(), "dir" to StringSpecTreeNode())
            )
        )
        configSpec.get<CompositeSpecTreeNode>("enabled")!!.children["py"] = ArraySpecTreeNode(
            description = "Plugins from Python's Package repositories",
            items = CompositeSpecTreeNode(
                namesOfRequired = mutableListOf("name"),
                children = mutableMapOf("name" to StringSpecTreeNode(), "version" to StringSpecTreeNode(), "repository" to StringSpecTreeNode())
            )
        )

        val pyPluginsData = project.extensions.get(PyPluginsData.Extension.key) ?: return

        pyPluginsData.pyPackages.forEach {
            project.pyPluginsEnvironment.install(it)
        }

        project.pyPluginsClient.initializeProject()
        project.pyPluginsClient.importPlugins()
        project.register(
            plugins = pyPluginsData.pyPackagesPlugins.map { PyPlugin(it.hash) } + pyPluginsData.pyModulesPlugins.map { PyPlugin(it.hash) }
        )
    }

    override fun tasks(project: Project): List<Task> = emptyList()

    @Suppress("UNCHECKED_CAST")
    override fun extensions(project: Project): List<Project.Extension<Any>> {
        return listOf(
            PyPluginsInterpreter.Extension,
            PyPluginsEnvironment.Extension,
            PyPluginsRepositories.Extension,
            PyPluginsData.Extension,
            PyPluginsInterop.Extension,
            PyPluginsClient.Extension
        ) as List<Project.Extension<Any>>
    }
}
