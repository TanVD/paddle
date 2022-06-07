package io.paddle.plugin.repositories

object EmbeddedPluginsRepository : AbstractJVMBasedPluginsRepository() {
    override val configPath: String
        get() = "META-INF/standard-plugins.yaml"
    override val classLoader: ClassLoader
        get() = this.javaClass.classLoader
}
