package io.paddle.idea.utils

import io.paddle.plugin.plugins
import io.paddle.project.Project
import io.paddle.specification.tree.JsonSchemaSpecification
import io.paddle.terminal.TextOutput
import io.paddle.utils.config.Configuration
import java.io.File

object PaddleProject {
    var currentProject: Project? = null

    fun load(file: File, workDir: File, output: TextOutput = TextOutput.Console): Project {
        val config = Configuration.from(file)
        val configSpec = JsonSchemaSpecification.base

        return Project(config, configSpec, workDir, output).also {
            it.register(it.plugins.enabled)
            currentProject = it
        }
    }
}
