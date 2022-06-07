package io.paddle.plugin.repositories

import io.paddle.plugin.*
import io.paddle.project.Project
import io.paddle.utils.config.PluginsConfig
import io.paddle.utils.ext.Extendable
import io.paddle.utils.plugins.PluginName
import io.paddle.utils.plugins.PluginsRepoName
import org.apache.commons.collections4.Trie
import org.apache.commons.collections4.trie.PatriciaTrie


val Project.jarPluginsRepositories: JarPluginsRepositories
    get() = extensions.get(JarPluginsRepositories.Extension.key)!!

class JarPluginsRepositories(private val storage: Map<PluginsRepoName, JarPluginsRepository>) {

    private val index: Trie<PluginName, List<JarPluginsRepository>>

    init {
        val pluginsToRepos: HashMap<PluginName, MutableList<JarPluginsRepository>> = hashMapOf()
        storage.values.forEach { repo ->
            repo.availablePluginsNames.forEach { pluginName ->
                pluginsToRepos[pluginName]?.add(repo) ?: run {
                    pluginsToRepos[pluginName] = mutableListOf(repo)
                }
            }
        }
        // https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/trie/PatriciaTrie.html
        index = PatriciaTrie(pluginsToRepos)
    }

    object Extension : Project.Extension<JarPluginsRepositories> {
        override val key: Extendable.Key<JarPluginsRepositories> = Extendable.Key()

        override fun create(project: Project): JarPluginsRepositories {
            val config = object : PluginsConfig(project) {
                val jarRepos by repositories(withAttrs = setOf("jar"))
            }
            val repos: List<JarPluginsRepository> = config.jarRepos.map {
                val name = it["name"]
                requireNotNull(name)
                val file = it["jar"]?.let { filename -> project.workDir.resolve(filename) }
                requireNotNull(file)
                require(file.exists())
                require(file.isFile && file.extension == "jar")

                JarPluginsRepository(name, file)
            }

            return JarPluginsRepositories(repos.associateBy { it.name })
        }
    }

    operator fun get(repoName: PluginsRepoName): JarPluginsRepository? = storage[repoName]

    operator fun get(repoName: PluginsRepoName, pluginName: PluginName): Plugin? = storage[repoName]?.plugin(pluginName)

    fun contains(repoName: PluginsRepoName): Boolean = storage.containsKey(repoName)

    fun findAvailablePluginsBy(prefix: String): Map<PluginName, List<JarPluginsRepository>> {
        return index.prefixMap(prefix)
    }
}
