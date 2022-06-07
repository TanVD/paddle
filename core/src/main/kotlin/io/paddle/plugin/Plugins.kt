package io.paddle.plugin

import io.paddle.plugin.repositories.EmbeddedPluginsRepository
import io.paddle.plugin.repositories.jarPluginsRepositories
import io.paddle.plugin.standard.StandardPlugin
import io.paddle.project.Project
import io.paddle.utils.config.PluginsConfig
import io.paddle.utils.ext.Extendable
import io.paddle.utils.plugins.PluginName
import io.paddle.utils.plugins.PluginsRepoName

val Project.plugins: Plugins
    get() = extensions.get(Plugins.Extension.key)!!

class Plugins(private val _enabled: MutableList<Plugin>) {
    val enabled: List<Plugin>
        get() = _enabled

    object Extension : Project.Extension<Plugins> {
        override val key: Extendable.Key<Plugins> = Extendable.Key()

        override fun create(project: Project): Plugins {
            val config = object : PluginsConfig(project) {
                val embeddedPlugins by plugins<String>(type = "embedded")
            }

            val enabled: MutableList<Plugin> = mutableListOf(StandardPlugin)
            EmbeddedPluginsRepository.plugins(config.embeddedPlugins + "pyinjector").forEach {
                enabled.add(it)
            }

            project.extensions.get(LocalPluginsDescriptors.Extension.key)?.forJarPlugins?.forEach {
                project.jarPluginsRepositories[it.repoName, it.pluginName]?.apply {
                    enabled.add(this@apply)
                } ?: throw IllegalArgumentException("Cannot find jar plugin with name `${it.pluginName}` inside repository `${it.repoName}`")
            }

            return Plugins(enabled)
        }
    }
}

class LocalPluginsDescriptors(val forJarPlugins: List<Descriptor>, val others: List<Descriptor>) {
    object Extension : Project.Extension<LocalPluginsDescriptors> {
        override val key: Extendable.Key<LocalPluginsDescriptors> = Extendable.Key()

        override fun create(project: Project): LocalPluginsDescriptors {
            val config = object : PluginsConfig(project) {
                val localPlugins by plugins<Map<String, String>>(type = "local")
            }

            val (jars, others) = config.localPlugins.map {
                val name = it["name"]
                requireNotNull(name)
                val repoName: PluginsRepoName? = it["repository"]
                requireNotNull(repoName)
                Descriptor(name, repoName)
            }.partition {
                project.jarPluginsRepositories.contains(it.repoName)
            }

            return LocalPluginsDescriptors(jars, others)
        }
    }

    data class Descriptor(val pluginName: PluginName, val repoName: PluginsRepoName)
}
