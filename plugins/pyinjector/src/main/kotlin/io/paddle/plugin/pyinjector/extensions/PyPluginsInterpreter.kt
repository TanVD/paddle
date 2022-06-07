package io.paddle.plugin.pyinjector.extensions

import io.paddle.plugin.python.dependencies.PyInterpreter
import io.paddle.project.Project
import io.paddle.utils.ext.Extendable

val Project.pyPluginsInterpreter: PyPluginsInterpreter
    get() = extensions.get(PyPluginsInterpreter.Extension.key)!!

class PyPluginsInterpreter(project: Project, pythonVersion: PyInterpreter.Version) {
    val resolved: PyInterpreter = PyInterpreter.find(pythonVersion, project)

    object Extension : Project.Extension<PyPluginsInterpreter> {
        override val key: Extendable.Key<PyPluginsInterpreter> = Extendable.Key()

        override fun create(project: Project): PyPluginsInterpreter {
            // todo: get version from global paddle settings file
            val version: PyInterpreter.Version = PyInterpreter.Version("3.8")
            return PyPluginsInterpreter(project, version)
        }
    }
}
