package io.paddle.plugin.pyinjector.extensions

import io.paddle.plugin.LocalPluginsDescriptors
import io.paddle.plugin.pyinjector.dependencies.PyModule
import io.paddle.plugin.python.dependencies.packages.PyPackage
import io.paddle.plugin.python.dependencies.resolvers.PipResolver
import io.paddle.plugin.python.extensions.Requirements
import io.paddle.project.Project
import io.paddle.utils.config.PluginsConfig
import io.paddle.utils.ext.Extendable
import io.paddle.utils.plugins.PluginName

data class PyPackagePluginData(val name: PluginName, val pyPackage: PyPackage) {
    val hash: String
        get() = "$name:${pyPackage.distributionUrl}"
}

data class PyModulePluginData(val name: PluginName, val pyModule: PyModule) {
    val hash: String
        get() = "$name:${pyModule.repository.absolutePathTo}:${pyModule.relativePathTo}"
}

class PyPluginsData(
    project: Project,
    pyPackageDescriptors: List<Requirements.Descriptor>,
    pyModuleDescriptors: List<LocalPluginsDescriptors.Descriptor>,
    pyPluginToPackage: List<Pair<PluginName, String>>
) {

    val pyPackages: Collection<PyPackage> = PipResolver.resolve(
        project, pyPackageDescriptors,
        project.pyPluginsRepositories.withPyPackages,
        project.pyPluginsEnvironment.localInterpreterPath
    ) + project.pyPluginsEnvironment.venv.pyPackages

    val pyPackagesPlugins: Collection<PyPackagePluginData> = pyPluginToPackage.map {
        pyPackages.find { pkg -> pkg.name == it.second }?.run {
            PyPackagePluginData(it.first, this)
        } ?: throw IllegalArgumentException("Cannot find plugin with name `${it.second}`")
    }

    val pyModulesPlugins: Collection<PyModulePluginData> = resolveModules(project, pyModuleDescriptors)

    val pyModules: Collection<PyModule> = pyModulesPlugins.map { it.pyModule }

    object Extension : Project.Extension<PyPluginsData> {
        override val key: Extendable.Key<PyPluginsData> = Extendable.Key()

        override fun create(project: Project): PyPluginsData {
            val config = object : PluginsConfig(project) {
                val plugins: List<Map<String, String>> by plugins(type = "py")
            }
            val pyPluginToPackage: MutableList<Pair<PluginName, String>> = mutableListOf()
            val pyPackageDescriptors = config.plugins.map {
                val pluginName = it["name"]!!
                val packageName = it["library"]!!
                pyPluginToPackage.add(pluginName to packageName)

                Requirements.Descriptor(packageName, it["version"], it["repository"])
            }
            val pyModuleDescriptors = project.extensions.get(LocalPluginsDescriptors.Extension.key)?.others ?: emptyList()

            project.pyPluginsEnvironment.initialize()

            return PyPluginsData(project, pyPackageDescriptors, pyModuleDescriptors, pyPluginToPackage)
        }
    }

    private fun resolveModules(project: Project, descriptors: List<LocalPluginsDescriptors.Descriptor>): List<PyModulePluginData> {
        return descriptors.map {
            project.pyPluginsRepositories.withPyModules[it.repoName]?.sourceModuleFor(it.pluginName)?.run {
                PyModulePluginData(it.pluginName, this)
            } ?: throw IllegalArgumentException("Cannot find plugin `${it.pluginName}` in repository `${it.repoName}`")
        }
    }
}
