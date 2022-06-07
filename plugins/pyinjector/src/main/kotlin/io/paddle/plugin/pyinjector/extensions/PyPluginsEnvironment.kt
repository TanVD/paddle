package io.paddle.plugin.pyinjector.extensions

import io.paddle.plugin.pyinjector.dependencies.PluginsTempVenvManager
import io.paddle.plugin.python.dependencies.*
import io.paddle.plugin.python.dependencies.packages.PyPackage
import io.paddle.plugin.python.extensions.AbstractEnvironment
import io.paddle.project.Project
import io.paddle.utils.ext.Extendable
import java.io.File
import java.nio.file.Path

val Project.pyPluginsEnvironment: PyPluginsEnvironment
    get() = extensions.get(PyPluginsEnvironment.Extension.key)!!

class PluginsVenvDir(directory: File) : VenvDir(directory) {
    override fun getInterpreterPath(project: Project): Path {
        return bin.resolve(project.pyPluginsInterpreter.resolved.version.executableName).toPath()
    }
}

class PyPluginsEnvironment(project: Project, venv: PluginsVenvDir) : AbstractEnvironment(project, venv) {

    private val tempVenvManager: AbstractTempVenvManager by lazy { PluginsTempVenvManager.create(project) }

    override val initInterpreterPath: Path
        get() = project.pyPluginsInterpreter.resolved.path

    object Extension : Project.Extension<PyPluginsEnvironment> {
        override val key: Extendable.Key<PyPluginsEnvironment> = Extendable.Key()

        override fun create(project: Project): PyPluginsEnvironment {
            return PyPluginsEnvironment(project, PluginsVenvDir(File(project.workDir, ".venv_plugins")))
        }
    }

    override fun install(pkg: PyPackage) {
        if (venv.hasInstalledPackage(pkg)) return
        val cachedPkg = GlobalCacheRepository.findPackage(pkg, project, tempVenvManager)
        GlobalCacheRepository.createSymlinkToPackage(cachedPkg, venv)
    }
}
