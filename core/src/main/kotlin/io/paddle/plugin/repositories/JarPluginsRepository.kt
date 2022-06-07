package io.paddle.plugin.repositories

import io.paddle.utils.plugins.PluginsRepoName
import java.io.File
import java.net.URLClassLoader


class JarPluginsRepository(val name: PluginsRepoName, private val jarFile: File) : AbstractJVMBasedPluginsRepository() {
    override val classLoader: ClassLoader
        get() = URLClassLoader(arrayOf(jarFile.toURI().toURL()), this.javaClass.classLoader)
}
