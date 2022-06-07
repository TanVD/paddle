package io.paddle.plugin.standard.extensions

import io.paddle.project.Project
import io.paddle.utils.config.ConfigurationView
import io.paddle.utils.ext.Extendable
import java.io.File

val Project.roots: Roots
    get() = extensions.get(Roots.Extension.key)!!

class Roots(val sources: MutableList<File>, val tests: MutableList<File>, val resources: MutableList<File>) {
    object Extension : Project.Extension<Roots> {
        override val key: Extendable.Key<Roots> = Extendable.Key()

        override fun create(project: Project): Roots {
            val config = object : ConfigurationView("roots", project.config) {
                val sources by list("sources", default = listOf("src"))
                val tests by list("tests", default = listOf("tests"))
                val resources by list("resources", default = listOf("resources"))
            }

            return Roots(
                sources = config.sources.map { File(project.workDir, it) }.toMutableList(),
                tests = config.tests.map { File(project.workDir, it) }.toMutableList(),
                resources = config.resources.map { File(project.workDir, it) }.toMutableList()
            )
        }
    }

}
