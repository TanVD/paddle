package io.paddle.plugin.pyinjector.extensions

import io.paddle.plugin.pyinjector.dependencies.repositories.PyModuleRepositories
import io.paddle.plugin.python.dependencies.repositories.PyPackageRepositories
import io.paddle.plugin.python.extensions.Repositories
import io.paddle.project.Project
import io.paddle.utils.config.PluginsConfig
import io.paddle.utils.ext.Extendable

val Project.pyPluginsRepositories: PyPluginsRepositories
    get() = extensions.get(PyPluginsRepositories.Extension.key)!!

class PyPluginsRepositories(
    project: Project, pyPackageReposDescriptors: List<Repositories.Descriptor>,
    pyModuleReposDescriptors: List<PyModuleRepositories.ModuleRepoDescriptor>
) {

    val withPyPackages: PyPackageRepositories = PyPackageRepositories.resolve(pyPackageReposDescriptors)
    val withPyModules: PyModuleRepositories = PyModuleRepositories.resolve(project.workDir.toPath(), pyModuleReposDescriptors)

    object Extension : Project.Extension<PyPluginsRepositories> {
        override val key: Extendable.Key<PyPluginsRepositories> = Extendable.Key()

        override fun create(project: Project): PyPluginsRepositories {
            val plugins = object : PluginsConfig(project) {
                val packageRepos by repositories(withAttrs = setOf("url"))
                val moduleRepos by repositories(withAttrs = setOf("dir"))
            }

            val pyPackageReposDescriptions: List<Repositories.Descriptor> = plugins.packageRepos.map {
                Repositories.Descriptor(
                    it["name"]!!,
                    it["url"]!!,
                    it["default"].toBoolean(),
                    it["secondary"].toBoolean()
                )
            }

            val pyModuleReposDescriptions: List<PyModuleRepositories.ModuleRepoDescriptor> = plugins.moduleRepos.map {
                PyModuleRepositories.ModuleRepoDescriptor(it["name"]!!, it["dir"]!!)
            }

            return PyPluginsRepositories(project, pyPackageReposDescriptions, pyModuleReposDescriptions)
        }
    }
}
