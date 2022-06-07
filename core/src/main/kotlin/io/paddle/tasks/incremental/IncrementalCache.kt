package io.paddle.tasks.incremental

import io.paddle.project.Project
import io.paddle.utils.hash.Hashable
import io.paddle.utils.json.JSON
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import java.io.File

internal class IncrementalCache(val project: Project) {
    private val storage = File(project.workDir, ".paddle/cache.json")

    private var cache: Map<String, Cache>
        // TODO: make it better, maybe using concurrent hash map
        @Synchronized
        get() {
            storage.parentFile.mkdirs()
            return storage.takeIf { it.exists() }
                ?.let { JSON.parse(MapSerializer(String.serializer(), Cache.serializer()), it.readText()) }
                ?: emptyMap()
        }
        set(value) {
            storage.parentFile.mkdirs()
            storage.writeText(JSON.string(MapSerializer(String.serializer(), Cache.serializer()), value))
        }

    @Serializable
    private data class Cache(val input: String, val output: String)

    fun isUpToDate(id: String, input: Hashable, output: Hashable): Boolean {
        val current = cache[id] ?: return false
        return current.input == input.hash() && current.output == output.hash()
    }

    @Synchronized
    fun update(id: String, input: Hashable, output: Hashable) {
        cache = cache.toMutableMap().also {
            it[id] = Cache(input.hash(), output.hash())
        }
    }
}
