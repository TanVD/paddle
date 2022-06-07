package io.paddle.project

import io.paddle.execution.CommandExecutor
import io.paddle.execution.local.LocalCommandExecutor
import io.paddle.plugin.*
import io.paddle.plugin.repositories.JarPluginsRepositories
import io.paddle.specification.tree.JsonSchemaSpecification
import io.paddle.specification.tree.SpecializedConfigSpec
import io.paddle.terminal.*
import io.paddle.utils.config.Configuration
import io.paddle.utils.ext.Extendable
import io.paddle.utils.hash.StringHashable
import io.paddle.utils.yaml.YAML
import java.io.File

class Project(
    val config: Configuration, var configSpec: SpecializedConfigSpec<*>,
    val workDir: File = File("."), val output: TextOutput = TextOutput.Console,
) {
    interface Extension<V : Any> {
        val key: Extendable.Key<V>

        fun create(project: Project): V
    }

    val id: String = StringHashable(workDir.absolutePath).hash()
    val tasks = Tasks()
    val extensions = Extendable()
    var executor: CommandExecutor = LocalCommandExecutor(output)
    val terminal = Terminal(output)

    val buildFile: File = workDir.resolve("paddle.yaml")
    val yaml: MutableMap<String, Any> = buildFile.readText().let { YAML.parse(it) }


    init {
        extensions.register(JarPluginsRepositories.Extension.key, JarPluginsRepositories.Extension.create(this))
        extensions.register(LocalPluginsDescriptors.Extension.key, LocalPluginsDescriptors.Extension.create(this))
        extensions.register(Plugins.Extension.key, Plugins.Extension.create(this))
    }

    fun register(plugin: Plugin) {
        for (extension in plugin.extensions(this)) {
            extensions.register(extension.key, extension.create(this))
        }

        for (task in plugin.tasks(this)) {
            tasks.register(task)
        }

        plugin.configure(this)
    }

    fun register(plugins: Iterable<Plugin>) {
        plugins.forEach(::register)
    }

    fun execute(id: String) {
        val task = tasks.get(id) ?: run {
            terminal.commands.stderr(CommandOutput.Command.Task(id, CommandOutput.Command.Task.Status.UNKNOWN))
            return
        }
        task.run()
    }

    companion object {
        fun load(configFile: File): Project {
            return Project(
                config = Configuration.from(configFile),
                configSpec = JsonSchemaSpecification.base
            )
        }
    }
}
