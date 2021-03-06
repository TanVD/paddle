package io.paddle.plugin.python.extensions

import io.paddle.project.PaddleProject
import io.paddle.project.extensions.routeAsString
import io.paddle.utils.ext.Extendable
import io.paddle.utils.hash.*
import java.io.File

val PaddleProject.buildEnvironment: BuildEnvironment
    get() = checkNotNull(this.extensions.get(BuildEnvironment.Extension.key)) { "Could not load extension BuildEnvironment for project $routeAsString" }

class BuildEnvironment(val project: PaddleProject) : Hashable {
    val pyprojectToml: File
        get() = project.workDir.resolve("pyproject.toml")

    val setupCfg: File
        get() = project.workDir.resolve("setup.cfg")

    val readme: File?
        get() = project.workDir.resolve("README.md").takeIf { it.exists() }
            ?: project.workDir.resolve("README").takeIf { it.exists() }

    object Extension : PaddleProject.Extension<BuildEnvironment> {
        override val key: Extendable.Key<BuildEnvironment> = Extendable.Key()

        override fun create(project: PaddleProject): BuildEnvironment {
            return BuildEnvironment(project)
        }
    }

    override fun hash(): String {
        return AggregatedHashable(listOf(pyprojectToml.hashable(), setupCfg.hashable())).hash()
    }
}
