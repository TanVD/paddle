package io.paddle.plugin.pyinjector.dependencies.repositories

import io.paddle.plugin.pyinjector.dependencies.PyModule
import io.paddle.utils.plugins.PluginName
import java.nio.file.Path

typealias PyModuleRepoName = String

class PyModuleRepository(val name: PyModuleRepoName, val absolutePathTo: Path, modules: Map<PluginName, Path>) {
    private val modules: Map<PluginName, PyModule> = modules.mapValues { PyModule(it.value, this) }

    val availablePlugins: Set<PluginName>
        get() = modules.keys

    fun sourceModuleFor(plugin: PluginName) = modules[plugin]
}
