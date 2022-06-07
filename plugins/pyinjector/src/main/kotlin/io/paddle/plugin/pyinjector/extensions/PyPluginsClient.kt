package io.paddle.plugin.pyinjector.extensions

import io.grpc.Channel
import io.paddle.plugin.interop.*
import io.paddle.project.Project
import io.paddle.utils.ext.Extendable
import kotlinx.coroutines.runBlocking

val Project.pyPluginsClient: PyPluginsClient
    get() = extensions.get(PyPluginsClient.Extension.key)!!

class PyPluginsClient(val project: Project, channel: Channel) {

    private val client = PluginsGrpcKt.PluginsCoroutineStub(channel)

    object Extension : Project.Extension<PyPluginsClient> {
        override val key: Extendable.Key<PyPluginsClient> = Extendable.Key()

        override fun create(project: Project): PyPluginsClient {
            return PyPluginsClient(project, project.extensions.get(PyPluginsInterop.Extension.key)!!.channel)
        }
    }

    fun initializeProject() = runBlocking {
        client.initializeProjectStub(
            initializeProjectRequest {
                this.projectId = project.id
                this.workingDir = project.workDir.absolutePath
                this.pluginsSitePackages = project.pyPluginsEnvironment.venv.sitePackages.absolutePath
            }
        )
    }

    fun importPlugins() = runBlocking {
        val pyPluginsData = project.extensions.get(PyPluginsData.Extension.key)!!

        client.importPyPackagePlugins(
            importPyPackagePluginsRequest {
                this.projectId = project.id
                this.packages.addAll(pyPluginsData.pyPackages.map {
                    pyPackageInfo {
                        this.packageName = it.name
                        this.distributionUrl = it.distributionUrl
                    }
                })
            }
        )

        client.importPyModulePlugins(
            importPyModulePluginsRequest {
                this.projectId = project.id
                this.modules.addAll(pyPluginsData.pyModules.map {
                    pyModuleInfo {
                        this.absoluteRepoDir = it.repository.absolutePathTo.toString()
                        this.relativeDirToModule = it.relativePathTo.toString()
                    }
                })
            }
        )
    }

    suspend fun configure(pluginHash: String) {
        client.configure(
            processPluginRequest {
                this.projectId = project.id
                this.pluginHash = pluginHash
            }
        )
    }

    suspend fun tasks(pluginHash: String): List<TaskInfo> {
        return client.tasks(
            processPluginRequest {
                this.projectId = project.id
                this.pluginHash = pluginHash
            }
        ).tasksInfoList
    }

    suspend fun initialize(taskId: String) {
        client.initialize(
            processTaskRequest {
                this.projectId = project.id
                this.taskId = taskId
            }
        )
    }

    suspend fun act(taskId: String) {
        client.act(
            processTaskRequest {
                this.projectId = project.id
                this.taskId = taskId
            }
        )
    }
}
