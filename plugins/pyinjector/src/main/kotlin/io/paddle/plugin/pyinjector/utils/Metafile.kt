package io.paddle.plugin.pyinjector.utils

import io.paddle.utils.config.ConfigurationView
import io.paddle.utils.plugins.PluginName
import java.io.File

object Metafile {
    const val metafileName = "paddle-plugins.yaml"

    data class Description(val pluginName: PluginName, val filenameOrPath: String)

    /**
     * Parse a file with meta information about specified local plugins' repository.
     *
     * @param file  A file with information
     * @throws IllegalArgumentException If file has no necessary information
     *
     * File format:
     *      plugins:
     *          - name: [plugin-name-1]
     *            module: [filename or path]
     *
     *          - name: [plugin-name-2]
     *            module: [filename or path]
     */
    fun parse(file: File): List<Description> {
        val config = object : ConfigurationView("", from(file)) {
            val plugins: List<Map<String, String>> by list("plugins", emptyList())
        }
        return config.plugins.map {
            val name = it["name"]
            val module = it["module"]
            requireNotNull(name)
            requireNotNull(module)

            Description(name, module)
        }
    }
}
