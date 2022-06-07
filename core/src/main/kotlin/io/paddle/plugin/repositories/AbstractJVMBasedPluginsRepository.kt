package io.paddle.plugin.repositories

import io.paddle.plugin.Plugin
import io.paddle.utils.config.Configuration
import io.paddle.utils.resource.ResourceUtils

abstract class AbstractJVMBasedPluginsRepository {
    protected open val configPath = "META-INF/paddle-plugins.yaml"
    protected abstract val classLoader: ClassLoader

    private val pluginsIdsToClassnames: Map<String, String> by lazy {
        parseConfigFile()
    }

    val availablePluginsNames: Set<String>
        get() = pluginsIdsToClassnames.keys

    private fun parseConfigFile(): Map<String, String> {
        val pluginsConfig = Configuration.from(ResourceUtils.getResourceFileBy(classLoader, configPath)!!)
        val configView = object : Configuration() {
            val idsAndClasses by list<Map<String, String>>("plugins", emptyList())

            override fun <T> get(key: String): T? {
                return pluginsConfig.get(key)
            }
        }
        return hashMapOf<String, String>().also {
            configView.idsAndClasses.forEach { plugin -> it[plugin["id"]!!] = plugin["class"]!! }
        }
    }

    fun plugin(name: String): Plugin? {
        return pluginsIdsToClassnames[name]?.let { loadClassBy(it) }
    }

    private fun loadClassBy(classname: String): Plugin {
        return Class.forName(classname, true, classLoader).kotlin.objectInstance as Plugin
    }

    fun plugins(names: List<String>): List<Plugin> {
        return names.mapNotNull { plugin(it) }
    }
}
