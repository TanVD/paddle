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
import io.paddle.plugin.interop.PluginsGrpc.getServiceDescriptor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * Holder for Kotlin coroutine-based client and server APIs for io.paddle.plugin.interop.Plugins.
 */
object PluginsGrpcKt {
  const val SERVICE_NAME: String = PluginsGrpc.SERVICE_NAME

  @JvmStatic
  val serviceDescriptor: ServiceDescriptor
    get() = PluginsGrpc.getServiceDescriptor()

  val initializeProjectStubMethod: MethodDescriptor<InitializeProjectRequest, Empty>
    @JvmStatic
    get() = PluginsGrpc.getInitializeProjectStubMethod()

  val importPyModulePluginsMethod: MethodDescriptor<ImportPyModulePluginsRequest, Empty>
    @JvmStatic
    get() = PluginsGrpc.getImportPyModulePluginsMethod()

  val importPyPackagePluginsMethod: MethodDescriptor<ImportPyPackagePluginsRequest, Empty>
    @JvmStatic
    get() = PluginsGrpc.getImportPyPackagePluginsMethod()

  val configureMethod: MethodDescriptor<ProcessPluginRequest, Empty>
    @JvmStatic
    get() = PluginsGrpc.getConfigureMethod()

  val tasksMethod: MethodDescriptor<ProcessPluginRequest, GetTasksResponse>
    @JvmStatic
    get() = PluginsGrpc.getTasksMethod()

  val initializeMethod: MethodDescriptor<ProcessTaskRequest, Empty>
    @JvmStatic
    get() = PluginsGrpc.getInitializeMethod()

  val actMethod: MethodDescriptor<ProcessTaskRequest, Empty>
    @JvmStatic
    get() = PluginsGrpc.getActMethod()

  /**
   * A stub for issuing RPCs to a(n) io.paddle.plugin.interop.Plugins service as suspending
   * coroutines.
   */
  @StubFor(PluginsGrpc::class)
  class PluginsCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<PluginsCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): PluginsCoroutineStub =
        PluginsCoroutineStub(channel, callOptions)

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
    suspend fun initializeProjectStub(request: InitializeProjectRequest, headers: Metadata =
        Metadata()): Empty = unaryRpc(
      channel,
      PluginsGrpc.getInitializeProjectStubMethod(),
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
    suspend fun importPyModulePlugins(request: ImportPyModulePluginsRequest, headers: Metadata =
        Metadata()): Empty = unaryRpc(
      channel,
      PluginsGrpc.getImportPyModulePluginsMethod(),
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
    suspend fun importPyPackagePlugins(request: ImportPyPackagePluginsRequest, headers: Metadata =
        Metadata()): Empty = unaryRpc(
      channel,
      PluginsGrpc.getImportPyPackagePluginsMethod(),
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
    suspend fun configure(request: ProcessPluginRequest, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      PluginsGrpc.getConfigureMethod(),
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
    suspend fun tasks(request: ProcessPluginRequest, headers: Metadata = Metadata()):
        GetTasksResponse = unaryRpc(
      channel,
      PluginsGrpc.getTasksMethod(),
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
    suspend fun initialize(request: ProcessTaskRequest, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      PluginsGrpc.getInitializeMethod(),
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
    suspend fun act(request: ProcessTaskRequest, headers: Metadata = Metadata()): Empty = unaryRpc(
      channel,
      PluginsGrpc.getActMethod(),
      request,
      callOptions,
      headers
    )}

  /**
   * Skeletal implementation of the io.paddle.plugin.interop.Plugins service based on Kotlin
   * coroutines.
   */
  abstract class PluginsCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Plugins.InitializeProjectStub.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun initializeProjectStub(request: InitializeProjectRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Plugins.InitializeProjectStub is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Plugins.ImportPyModulePlugins.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun importPyModulePlugins(request: ImportPyModulePluginsRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Plugins.ImportPyModulePlugins is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Plugins.ImportPyPackagePlugins.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun importPyPackagePlugins(request: ImportPyPackagePluginsRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Plugins.ImportPyPackagePlugins is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Plugins.Configure.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun configure(request: ProcessPluginRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Plugins.Configure is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Plugins.Tasks.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun tasks(request: ProcessPluginRequest): GetTasksResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Plugins.Tasks is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Plugins.Initialize.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun initialize(request: ProcessTaskRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Plugins.Initialize is unimplemented"))

    /**
     * Returns the response to an RPC for io.paddle.plugin.interop.Plugins.Act.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun act(request: ProcessTaskRequest): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method io.paddle.plugin.interop.Plugins.Act is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = PluginsGrpc.getInitializeProjectStubMethod(),
      implementation = ::initializeProjectStub
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = PluginsGrpc.getImportPyModulePluginsMethod(),
      implementation = ::importPyModulePlugins
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = PluginsGrpc.getImportPyPackagePluginsMethod(),
      implementation = ::importPyPackagePlugins
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = PluginsGrpc.getConfigureMethod(),
      implementation = ::configure
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = PluginsGrpc.getTasksMethod(),
      implementation = ::tasks
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = PluginsGrpc.getInitializeMethod(),
      implementation = ::initialize
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = PluginsGrpc.getActMethod(),
      implementation = ::act
    )).build()
  }
}
