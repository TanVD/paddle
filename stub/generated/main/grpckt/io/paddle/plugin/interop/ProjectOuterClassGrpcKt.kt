package io.paddle.plugin.interop

import com.google.protobuf.Empty
import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import io.paddle.plugin.interop.ProjectGrpc.getServiceDescriptor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * Holder for Kotlin coroutine-based client and server APIs for io.paddle.plugin.interop.Project.
 */
object ProjectGrpcKt {
  const val SERVICE_NAME: String = ProjectGrpc.SERVICE_NAME

  @JvmStatic
  val serviceDescriptor: ServiceDescriptor
    get() = ProjectGrpc.getServiceDescriptor()

  val printMessageMethod: MethodDescriptor<PrintRequest, Empty>
    @JvmStatic
    get() = ProjectGrpc.getPrintMessageMethod()

  val executeCommandMethod: MethodDescriptor<ExecuteCommandRequest, Empty>
    @JvmStatic
    get() = ProjectGrpc.getExecuteCommandMethod()

  val getWorkingDirectoryMethod: MethodDescriptor<ProjectInfoRequest, WorkingDir>
    @JvmStatic
    get() = ProjectGrpc.getGetWorkingDirectoryMethod()

  val getDescriptionMethod: MethodDescriptor<ProjectInfoRequest, Description>
    @JvmStatic
    get() = ProjectGrpc.getGetDescriptionMethod()

  val getRootsMethod: MethodDescriptor<ProjectInfoRequest, Roots>
    @JvmStatic
    get() = ProjectGrpc.getGetRootsMethod()

  val addSourcesMethod: MethodDescriptor<AddPathsRequest, Empty>
    @JvmStatic
    get() = ProjectGrpc.getAddSourcesMethod()

  val addTestsMethod: MethodDescriptor<AddPathsRequest, Empty>
    @JvmStatic
    get() = ProjectGrpc.getAddTestsMethod()

  val addResourcesMethod: MethodDescriptor<AddPathsRequest, Empty>
    @JvmStatic
    get() = ProjectGrpc.getAddResourcesMethod()

  val getTasksNamesMethod: MethodDescriptor<ProjectInfoRequest, Tasks>
    @JvmStatic
    get() = ProjectGrpc.getGetTasksNamesMethod()

  val runTaskMethod: MethodDescriptor<ProcessTaskRequest, Empty>
    @JvmStatic
    get() = ProjectGrpc.getRunTaskMethod()

  val addCleanLocationMethod: MethodDescriptor<AddPathsRequest, Empty>
    @JvmStatic
    get() = ProjectGrpc.getAddCleanLocationMethod()

  val getConfigurationSpecificationMethod: MethodDescriptor<ProjectInfoRequest, CompositeSpecNode>
    @JvmStatic
    get() = ProjectGrpc.getGetConfigurationSpecificationMethod()

  val updateConfigurationSpecificationMethod: MethodDescriptor<UpdateConfigSpecRequest, Empty>
    @JvmStatic
    get() = ProjectGrpc.getUpdateConfigurationSpecificationMethod()

  /**
   * A stub for issuing RPCs to a(n) io.paddle.plugin.interop.Project service as suspending
   * coroutines.
   */
  @StubFor(ProjectGrpc::class)
  class ProjectCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<ProjectCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): ProjectCoroutineStub =
        ProjectCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun printMessage(request: PrintRequest, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      ProjectGrpc.getPrintMessageMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun executeCommand(request: ExecuteCommandRequest, headers: Metadata = Metadata()):
        Empty = unaryRpc(
      channel,
      ProjectGrpc.getExecuteCommandMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getWorkingDirectory(request: ProjectInfoRequest, headers: Metadata = Metadata()):
        WorkingDir = unaryRpc(
      channel,
      ProjectGrpc.getGetWorkingDirectoryMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getDescription(request: ProjectInfoRequest, headers: Metadata = Metadata()):
        Description = unaryRpc(
      channel,
      ProjectGrpc.getGetDescriptionMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getRoots(request: ProjectInfoRequest, headers: Metadata = Metadata()): Roots =
        unaryRpc(
      channel,
      ProjectGrpc.getGetRootsMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun addSources(request: AddPathsRequest, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      ProjectGrpc.getAddSourcesMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun addTests(request: AddPathsRequest, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      ProjectGrpc.getAddTestsMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun addResources(request: AddPathsRequest, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      ProjectGrpc.getAddResourcesMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getTasksNames(request: ProjectInfoRequest, headers: Metadata = Metadata()): Tasks =
        unaryRpc(
      channel,
      ProjectGrpc.getGetTasksNamesMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun runTask(request: ProcessTaskRequest, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      ProjectGrpc.getRunTaskMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun addCleanLocation(request: AddPathsRequest, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      ProjectGrpc.getAddCleanLocationMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getConfigurationSpecification(request: ProjectInfoRequest, headers: Metadata =
        Metadata()): CompositeSpecNode = unaryRpc(
      channel,
      ProjectGrpc.getGetConfigurationSpecificationMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun updateConfigurationSpecification(request: UpdateConfigSpecRequest, headers: Metadata
        = Metadata()): Empty = unaryRpc(
      channel,
      ProjectGrpc.getUpdateConfigurationSpecificationMethod(),
      request,
      callOptions,
      headers
    )}

  /**
   * Skeletal implementation of the io.paddle.plugin.interop.Project service based on Kotlin
   * coroutines.
   */
  abstract class ProjectCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.PrintMessage.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun printMessage(request: PrintRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.PrintMessage is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.ExecuteCommand.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun executeCommand(request: ExecuteCommandRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.ExecuteCommand is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.GetWorkingDirectory.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getWorkingDirectory(request: ProjectInfoRequest): WorkingDir = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.GetWorkingDirectory is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.GetDescription.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getDescription(request: ProjectInfoRequest): Description = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.GetDescription is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.GetRoots.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getRoots(request: ProjectInfoRequest): Roots = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.GetRoots is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.AddSources.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun addSources(request: AddPathsRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.AddSources is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.AddTests.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun addTests(request: AddPathsRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.AddTests is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.AddResources.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun addResources(request: AddPathsRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.AddResources is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.GetTasksNames.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getTasksNames(request: ProjectInfoRequest): Tasks = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.GetTasksNames is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.RunTask.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun runTask(request: ProcessTaskRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.RunTask is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Project.AddCleanLocation.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun addCleanLocation(request: AddPathsRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.AddCleanLocation is unimplemented"))

    /**
     * Returns the response to an RPC for
     * io.paddle.plugin.interop.Project.GetConfigurationSpecification.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getConfigurationSpecification(request: ProjectInfoRequest): CompositeSpecNode =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.GetConfigurationSpecification is unimplemented"))

    /**
     * Returns the response to an RPC for
     * io.paddle.plugin.interop.Project.UpdateConfigurationSpecification.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun updateConfigurationSpecification(request: UpdateConfigSpecRequest): Empty =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Project.UpdateConfigurationSpecification is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getPrintMessageMethod(),
      implementation = ::printMessage
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getExecuteCommandMethod(),
      implementation = ::executeCommand
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getGetWorkingDirectoryMethod(),
      implementation = ::getWorkingDirectory
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getGetDescriptionMethod(),
      implementation = ::getDescription
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getGetRootsMethod(),
      implementation = ::getRoots
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getAddSourcesMethod(),
      implementation = ::addSources
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getAddTestsMethod(),
      implementation = ::addTests
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getAddResourcesMethod(),
      implementation = ::addResources
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getGetTasksNamesMethod(),
      implementation = ::getTasksNames
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getRunTaskMethod(),
      implementation = ::runTask
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getAddCleanLocationMethod(),
      implementation = ::addCleanLocation
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getGetConfigurationSpecificationMethod(),
      implementation = ::getConfigurationSpecification
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = ProjectGrpc.getUpdateConfigurationSpecificationMethod(),
      implementation = ::updateConfigurationSpecification
    )).build()
  }
}
