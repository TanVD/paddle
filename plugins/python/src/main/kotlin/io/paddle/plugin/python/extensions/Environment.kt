package io.paddle.plugin.python.extensions

import io.paddle.plugin.python.dependencies.GlobalCacheRepository
import io.paddle.plugin.python.dependencies.VenvDir
import io.paddle.plugin.python.dependencies.packages.PyPackage
import io.paddle.project.Project
import io.paddle.utils.config.ConfigurationView
import io.paddle.utils.ext.Extendable
import io.paddle.utils.hash.Hashable
import io.paddle.utils.hash.hashable
import java.io.File
import java.nio.file.Path


val Project.environment: Environment
    get() = extensions.get(Environment.Extension.key)!!

class Environment(project: Project, venv: VenvDir) : AbstractEnvironment(project, venv), Hashable {

    override val initInterpreterPath: Path
        get() = project.interpreter.resolved.path

    object Extension : Project.Extension<Environment> {
        override val key: Extendable.Key<Environment> = Extendable.Key()

        override fun create(project: Project): Environment {
            val config = object : ConfigurationView("environment", project.config) {
                val venv by string("path", default = ".venv")
            }

            return Environment(project, VenvDir(File(project.workDir, config.venv)))
        }
    }

    override fun install(pkg: PyPackage) {
        if (venv.hasInstalledPackage(pkg)) return
        val cachedPkg = GlobalCacheRepository.findPackage(pkg, project)
        GlobalCacheRepository.createSymlinkToPackage(cachedPkg, venv)
    }

    override fun hash(): String {
        return venv.hashable().hash()
    }
}
