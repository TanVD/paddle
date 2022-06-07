package io.paddle.plugin.pyinjector.interop

import io.paddle.plugin.Plugin
import io.paddle.plugin.pyinjector.extensions.pyPluginsClient
import io.paddle.plugin.pyinjector.interop.task.PyTask
import io.paddle.project.Project
import kotlinx.coroutines.runBlocking

class PyPlugin(private val hash: String) : Plugin {

    override fun configure(project: Project) = runBlocking {
        project.pyPluginsClient.configure(hash)
    }

    override fun tasks(project: Project): List<PyTask> = runBlocking {
        project.pyPluginsClient.tasks(hash).map {
            PyTask(it.id, it.group, project, it.depsIdsList)
        }
    }

    override fun extensions(project: Project): List<Project.Extension<Any>> {
        /*
        Extensions for Python-based Paddle plugins are not supported because of absent general interface.
        Necessary functionality for tasks must be implemented inside corresponding plugin's python module
        and called inside tasks.
         */
        return emptyList()
    }
}
