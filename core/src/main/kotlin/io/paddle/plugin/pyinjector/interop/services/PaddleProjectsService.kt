package io.paddle.plugin.pyinjector.interop.services

import com.google.protobuf.Empty
import io.grpc.Server
import io.grpc.ServerBuilder
import io.paddle.plugin.interop.*
import io.paddle.plugin.standard.extensions.descriptor
import io.paddle.plugin.standard.extensions.roots
import io.paddle.plugin.standard.tasks.clean
import io.paddle.project.Project
import io.paddle.specification.tree.CompositeSpecTreeNode
import io.paddle.specification.tree.JsonSchemaSpecification
import io.paddle.tasks.Task
import io.paddle.terminal.Terminal
import io.paddle.utils.absolutePathStrings
import io.paddle.utils.config.specification.NodesMapper
import io.paddle.utils.toFile
import java.io.File
import java.util.concurrent.ConcurrentHashMap

typealias ProjectId = String

class PaddleProjectsService private constructor(port: Int, private val projectsApi: ProjectsApiImpl) {
    private val server: Server = ServerBuilder.forPort(port).addService(projectsApi).build()

    val port: Int
        get() = server.port

    companion object {
        @Volatile
        private var instance: PaddleProjectsService? = null

        fun getInstance(port: Int): PaddleProjectsService = instance ?: synchronized(this) {
            instance ?: PaddleProjectsService(port, ProjectsApiImpl()).also { it.start(); instance = it }
        }
    }

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(Thread {
            println("*** shutting down gRPC server since JVM is shutting down")
            this@PaddleProjectsService.stop()
            println("*** server shut down")
        })
    }

    private fun stop() {
        server.shutdown()
    }

    fun register(project: Project) {
        projectsApi.register(project)
    }

    private class ProjectsApiImpl(initProjects: List<Project> = emptyList()) : ProjectGrpcKt.ProjectCoroutineImplBase() {
        private val projects: ConcurrentHashMap<ProjectId, Project> = initProjects.associateByTo(ConcurrentHashMap()) { it.id }

        fun register(project: Project) {
            projects[project.id] = project
        }

        private fun projectBy(id: ProjectId): Project {
            return projects[id] ?: error("Error while handling request from plugin. Could not find specified project!")
        }

        override suspend fun printMessage(request: PrintRequest): Empty {
            projectBy(request.projectId).terminal.also { terminal ->
                with(request.message) {
                    when (request.type) {
                        PrintRequest.Type.DEBUG -> terminal.debug(this)
                        PrintRequest.Type.INFO -> terminal.info(this)
                        PrintRequest.Type.WARN -> terminal.warn(this)
                        PrintRequest.Type.ERROR -> terminal.error(this)
                        PrintRequest.Type.OUT -> terminal.stdout(this)
                        else -> terminal.stderr(this)
                    }
                }
            }
            return Empty.getDefaultInstance()
        }

        override suspend fun executeCommand(request: ExecuteCommandRequest): Empty {
            projectBy(request.projectId).apply {
                executor.execute(
                    command = request.command, args = request.argsList, workingDir = workDir, terminal = terminal
                )
            }
            return Empty.getDefaultInstance()
        }

        override suspend fun getWorkingDirectory(request: ProjectInfoRequest): WorkingDir {
            return projectBy(request.projectId).let { project ->
                workingDir {
                    path = project.workDir.absolutePath
                }
            }
        }

        override suspend fun getDescription(request: ProjectInfoRequest): Description {
            return projectBy(request.projectId).run {
                with(descriptor) project@{
                    description {
                        name = this@project.name
                        version = this@project.version
                    }
                }
            }
        }

        override suspend fun getRoots(request: ProjectInfoRequest): Roots {
            return projectBy(request.projectId).run {
                with(roots) project@{
                    roots {
                        sources.addAll(this@project.sources.absolutePathStrings())
                        tests.addAll(this@project.tests.absolutePathStrings())
                        resources.addAll(this@project.resources.absolutePathStrings())
                    }
                }
            }
        }

        private fun MutableList<File>.addNewExistingPaths(request: AddPathsRequest, terminal: Terminal) {
            addAll(
                request.pathsList.map {
                    it.toFile().also { file ->
                        if (!file.exists()) {
                            terminal.error("File `${it}` does not exists!")
                        }
                    }
                }
            )
        }

        override suspend fun addSources(request: AddPathsRequest): Empty {
            with(projectBy(request.projectId)) {
                roots.sources.addNewExistingPaths(request, terminal)
            }
            return Empty.getDefaultInstance()
        }

        override suspend fun addTests(request: AddPathsRequest): Empty {
            with(projectBy(request.projectId)) {
                roots.tests.addNewExistingPaths(request, terminal)
            }
            return Empty.getDefaultInstance()
        }

        override suspend fun addResources(request: AddPathsRequest): Empty {
            with(projectBy(request.projectId)) {
                roots.resources.addNewExistingPaths(request, terminal)
            }
            return Empty.getDefaultInstance()
        }

        override suspend fun addCleanLocation(request: AddPathsRequest): Empty {
            with(projectBy(request.projectId)) {
                tasks.clean.locations.addNewExistingPaths(request, terminal)
            }
            return Empty.getDefaultInstance()
        }

        override suspend fun getTasksNames(request: ProjectInfoRequest): Tasks {
            return projectBy(request.projectId).let { project ->
                tasks {
                    names.addAll(project.tasks.all().map(Task::id))
                }
            }
        }

        override suspend fun runTask(request: ProcessTaskRequest): Empty {
            projectBy(request.projectId).execute(request.taskId)
            return Empty.getDefaultInstance()
        }

        override suspend fun getConfigurationSpecification(request: ProjectInfoRequest): CompositeSpecNode {
            return projectBy(request.projectId).configSpec.let {
                NodesMapper.toProtobufMessage(it.root)
            }
        }

        override suspend fun updateConfigurationSpecification(request: UpdateConfigSpecRequest): Empty {
            projectBy(request.projectId).configSpec = JsonSchemaSpecification(NodesMapper.toConfigSpec(request.configSpec) as CompositeSpecTreeNode)
            return Empty.getDefaultInstance()
        }
    }
}
