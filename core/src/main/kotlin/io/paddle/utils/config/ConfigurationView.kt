package io.paddle.utils.config

import io.paddle.project.Project
import kotlin.properties.ReadOnlyProperty

open class ConfigurationView(private val prefix: String, private val inner: Configuration) : Configuration() {
    override fun <T> get(key: String): T? {
        val path = if (key.isBlank()) prefix else "$prefix.$key"
        return inner.get(path)
    }
}

open class PluginsConfig(project: Project) : ConfigurationView("plugins", project.config) {
    fun repositories(withAttrs: Set<String>, default: ReposDescriptions = emptyList()): ReadOnlyProperty<Configuration, ReposDescriptions> =
        ReadOnlyProperty { thisRef, _ ->
            thisRef.get<ReposDescriptions>("repositories")
                ?.filter { repo ->
                    withAttrs.all { repo.containsKey(it) }
                } ?: default
        }

    @Suppress("UNCHECKED_CAST")
    fun <T> plugins(type: String, default: List<T> = emptyList()): ReadOnlyProperty<Configuration, List<T>> =
        ReadOnlyProperty { thisRef, _ ->
            thisRef.get("enabled.$type") ?: default
        }
}


typealias RepoDescription = Map<String, String>
typealias ReposDescriptions = List<RepoDescription>
typealias PluginDescriptor = RepoDescription
typealias PluginsDescriptors = List<PluginDescriptor>
