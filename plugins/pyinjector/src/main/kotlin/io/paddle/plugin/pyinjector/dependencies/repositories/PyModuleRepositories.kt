package io.paddle.plugin.pyinjector.dependencies.repositories

import io.paddle.plugin.pyinjector.utils.Metafile
import io.paddle.utils.exists
import io.paddle.utils.plugins.PluginName
import org.apache.commons.collections4.Trie
import org.apache.commons.collections4.trie.PatriciaTrie
import java.nio.file.Path

class PyModuleRepositories(private val storage: Map<PyModuleRepoName, PyModuleRepository>) {

    private val index: Trie<PluginName, List<PyModuleRepository>>

    init {
        val pluginsToRepos: HashMap<PluginName, MutableList<PyModuleRepository>> = hashMapOf()
        storage.values.forEach { repo ->
            repo.availablePlugins.forEach { moduleName ->
                pluginsToRepos[moduleName]?.add(repo) ?: run {
                    pluginsToRepos[moduleName] = mutableListOf(repo)
                }
            }
        }
        // https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/trie/PatriciaTrie.html
        index = PatriciaTrie(pluginsToRepos)
    }

    data class ModuleRepoDescriptor(val name: String, val directory: String)

    companion object {
        fun resolve(workingDir: Path, descriptions: List<ModuleRepoDescriptor>): PyModuleRepositories {
            val repos: MutableMap<PyModuleRepoName, PyModuleRepository> = hashMapOf()
            descriptions.forEach {
                repos[it.name] = resolve(workingDir, it)
            }
            return PyModuleRepositories(repos)
        }

        private fun resolve(workingDir: Path, description: ModuleRepoDescriptor): PyModuleRepository {
            val repoPath = workingDir.resolve(description.directory).toRealPath()
            require(repoPath.exists())
            val metafilePath = repoPath.resolve(Metafile.metafileName)
            require(metafilePath.exists())
            val modulesDescriptions: List<Metafile.Description> = Metafile.parse(metafilePath.toFile())

            return PyModuleRepository(description.name, repoPath, retrievePlugins(repoPath, modulesDescriptions))
        }

        private fun retrievePlugins(pathToRepo: Path, descriptions: List<Metafile.Description>): Map<PluginName, Path> {
            val (withPaths, withNames) = descriptions.partition {
                pathToRepo.resolve(it.filenameOrPath).exists()
            }
            val storage = withPaths.associateTo(hashMapOf()) {
                it.pluginName to Path.of(it.filenameOrPath)
            }
            val withFilenamesToResolve = withNames.groupByTo(hashMapOf(), Metafile.Description::filenameOrPath)
            pathToRepo.toFile().walkTopDown().forEach {
                if (it.isFile && it.extension == "py") {
                    (withFilenamesToResolve.remove(it.nameWithoutExtension) ?: withFilenamesToResolve.remove(it.name))?.onEach { description ->
                        storage[description.pluginName] = pathToRepo.relativize(it.toPath())
                    }
                }
            }
            requireAllModulesResolved(withFilenamesToResolve.keys)
            return storage
        }

        private fun requireAllModulesResolved(filenames: Collection<String>) {
            filenames.firstOrNull()?.also {
                throw IllegalArgumentException(
                    "Cannot find module file `${it}`. Ensure file exists or filename contains only name or absolute/relative path to repository directory"
                )
            }
        }
    }

    operator fun get(repoName: PyModuleRepoName): PyModuleRepository? = storage[repoName]

    fun findAvailablePluginsBy(prefix: String): Map<PluginName, List<PyModuleRepository>> {
        return index.prefixMap(prefix)
    }
}
