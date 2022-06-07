package io.paddle.plugin.pyinjector.interop.task

import io.paddle.plugin.pyinjector.extensions.pyPluginsClient
import io.paddle.project.Project
import io.paddle.tasks.Task
import kotlinx.coroutines.runBlocking

class PyTask(override val id: String, override val group: String, project: Project, private val dependenciesNames: List<String>) : Task(project) {

    override val dependencies: List<Task>
        get() = dependenciesNames.map { project.tasks.getOrFail(it) }

    override fun initialize() = runBlocking {
        project.pyPluginsClient.initialize(id)
    }

    override fun act() = runBlocking {
        actAsCoroutine()
    }

    override suspend fun actAsCoroutine() {
        project.pyPluginsClient.act(id)
    }
}
