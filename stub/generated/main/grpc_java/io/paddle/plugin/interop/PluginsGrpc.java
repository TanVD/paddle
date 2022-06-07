package io.paddle.plugin.interop;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Paddle's plugins service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: plugins.proto")
public final class PluginsGrpc {

  private PluginsGrpc() {}

  public static final String SERVICE_NAME = "io.paddle.plugin.interop.Plugins";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.InitializeProjectRequest,
      com.google.protobuf.Empty> getInitializeProjectStubMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InitializeProjectStub",
      requestType = io.paddle.plugin.interop.InitializeProjectRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.InitializeProjectRequest,
      com.google.protobuf.Empty> getInitializeProjectStubMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.InitializeProjectRequest, com.google.protobuf.Empty> getInitializeProjectStubMethod;
    if ((getInitializeProjectStubMethod = PluginsGrpc.getInitializeProjectStubMethod) == null) {
      synchronized (PluginsGrpc.class) {
        if ((getInitializeProjectStubMethod = PluginsGrpc.getInitializeProjectStubMethod) == null) {
          PluginsGrpc.getInitializeProjectStubMethod = getInitializeProjectStubMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.InitializeProjectRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InitializeProjectStub"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.InitializeProjectRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PluginsMethodDescriptorSupplier("InitializeProjectStub"))
              .build();
        }
      }
    }
    return getInitializeProjectStubMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ImportPyModulePluginsRequest,
      com.google.protobuf.Empty> getImportPyModulePluginsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ImportPyModulePlugins",
      requestType = io.paddle.plugin.interop.ImportPyModulePluginsRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ImportPyModulePluginsRequest,
      com.google.protobuf.Empty> getImportPyModulePluginsMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ImportPyModulePluginsRequest, com.google.protobuf.Empty> getImportPyModulePluginsMethod;
    if ((getImportPyModulePluginsMethod = PluginsGrpc.getImportPyModulePluginsMethod) == null) {
      synchronized (PluginsGrpc.class) {
        if ((getImportPyModulePluginsMethod = PluginsGrpc.getImportPyModulePluginsMethod) == null) {
          PluginsGrpc.getImportPyModulePluginsMethod = getImportPyModulePluginsMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ImportPyModulePluginsRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ImportPyModulePlugins"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ImportPyModulePluginsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PluginsMethodDescriptorSupplier("ImportPyModulePlugins"))
              .build();
        }
      }
    }
    return getImportPyModulePluginsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ImportPyPackagePluginsRequest,
      com.google.protobuf.Empty> getImportPyPackagePluginsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ImportPyPackagePlugins",
      requestType = io.paddle.plugin.interop.ImportPyPackagePluginsRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ImportPyPackagePluginsRequest,
      com.google.protobuf.Empty> getImportPyPackagePluginsMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ImportPyPackagePluginsRequest, com.google.protobuf.Empty> getImportPyPackagePluginsMethod;
    if ((getImportPyPackagePluginsMethod = PluginsGrpc.getImportPyPackagePluginsMethod) == null) {
      synchronized (PluginsGrpc.class) {
        if ((getImportPyPackagePluginsMethod = PluginsGrpc.getImportPyPackagePluginsMethod) == null) {
          PluginsGrpc.getImportPyPackagePluginsMethod = getImportPyPackagePluginsMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ImportPyPackagePluginsRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ImportPyPackagePlugins"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ImportPyPackagePluginsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PluginsMethodDescriptorSupplier("ImportPyPackagePlugins"))
              .build();
        }
      }
    }
    return getImportPyPackagePluginsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessPluginRequest,
      com.google.protobuf.Empty> getConfigureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Configure",
      requestType = io.paddle.plugin.interop.ProcessPluginRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessPluginRequest,
      com.google.protobuf.Empty> getConfigureMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessPluginRequest, com.google.protobuf.Empty> getConfigureMethod;
    if ((getConfigureMethod = PluginsGrpc.getConfigureMethod) == null) {
      synchronized (PluginsGrpc.class) {
        if ((getConfigureMethod = PluginsGrpc.getConfigureMethod) == null) {
          PluginsGrpc.getConfigureMethod = getConfigureMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProcessPluginRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Configure"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProcessPluginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PluginsMethodDescriptorSupplier("Configure"))
              .build();
        }
      }
    }
    return getConfigureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessPluginRequest,
      io.paddle.plugin.interop.GetTasksResponse> getTasksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Tasks",
      requestType = io.paddle.plugin.interop.ProcessPluginRequest.class,
      responseType = io.paddle.plugin.interop.GetTasksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessPluginRequest,
      io.paddle.plugin.interop.GetTasksResponse> getTasksMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessPluginRequest, io.paddle.plugin.interop.GetTasksResponse> getTasksMethod;
    if ((getTasksMethod = PluginsGrpc.getTasksMethod) == null) {
      synchronized (PluginsGrpc.class) {
        if ((getTasksMethod = PluginsGrpc.getTasksMethod) == null) {
          PluginsGrpc.getTasksMethod = getTasksMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProcessPluginRequest, io.paddle.plugin.interop.GetTasksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Tasks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProcessPluginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.GetTasksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PluginsMethodDescriptorSupplier("Tasks"))
              .build();
        }
      }
    }
    return getTasksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest,
      com.google.protobuf.Empty> getInitializeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Initialize",
      requestType = io.paddle.plugin.interop.ProcessTaskRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest,
      com.google.protobuf.Empty> getInitializeMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest, com.google.protobuf.Empty> getInitializeMethod;
    if ((getInitializeMethod = PluginsGrpc.getInitializeMethod) == null) {
      synchronized (PluginsGrpc.class) {
        if ((getInitializeMethod = PluginsGrpc.getInitializeMethod) == null) {
          PluginsGrpc.getInitializeMethod = getInitializeMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProcessTaskRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Initialize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProcessTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PluginsMethodDescriptorSupplier("Initialize"))
              .build();
        }
      }
    }
    return getInitializeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest,
      com.google.protobuf.Empty> getActMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Act",
      requestType = io.paddle.plugin.interop.ProcessTaskRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest,
      com.google.protobuf.Empty> getActMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest, com.google.protobuf.Empty> getActMethod;
    if ((getActMethod = PluginsGrpc.getActMethod) == null) {
      synchronized (PluginsGrpc.class) {
        if ((getActMethod = PluginsGrpc.getActMethod) == null) {
          PluginsGrpc.getActMethod = getActMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProcessTaskRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Act"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProcessTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PluginsMethodDescriptorSupplier("Act"))
              .build();
        }
      }
    }
    return getActMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PluginsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PluginsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PluginsStub>() {
        @java.lang.Override
        public PluginsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PluginsStub(channel, callOptions);
        }
      };
    return PluginsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PluginsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PluginsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PluginsBlockingStub>() {
        @java.lang.Override
        public PluginsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PluginsBlockingStub(channel, callOptions);
        }
      };
    return PluginsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PluginsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PluginsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PluginsFutureStub>() {
        @java.lang.Override
        public PluginsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PluginsFutureStub(channel, callOptions);
        }
      };
    return PluginsFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Paddle's plugins service definition.
   * </pre>
   */
  public static abstract class PluginsImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * Initializes project stub on Python Plugins Server.
     * </pre>
     */
    public void initializeProjectStub(io.paddle.plugin.interop.InitializeProjectRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInitializeProjectStubMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Imports plugins from specified local python modules into Python Plugins Server.
     * </pre>
     */
    public void importPyModulePlugins(io.paddle.plugin.interop.ImportPyModulePluginsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getImportPyModulePluginsMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Imports plugins from specified python packages into Python Plugins Server.
     * </pre>
     */
    public void importPyPackagePlugins(io.paddle.plugin.interop.ImportPyPackagePluginsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getImportPyPackagePluginsMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Executes configure of specified project with specified plugin.
     * </pre>
     */
    public void configure(io.paddle.plugin.interop.ProcessPluginRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfigureMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Requests info about all tasks of specified plugin.
     * </pre>
     */
    public void tasks(io.paddle.plugin.interop.ProcessPluginRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.GetTasksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTasksMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Initializes specified task with respect to specified project.
     * </pre>
     */
    public void initialize(io.paddle.plugin.interop.ProcessTaskRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInitializeMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Executes action of specified task with respect to specified project.
     * </pre>
     */
    public void act(io.paddle.plugin.interop.ProcessTaskRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getActMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInitializeProjectStubMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.InitializeProjectRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_INITIALIZE_PROJECT_STUB)))
          .addMethod(
            getImportPyModulePluginsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ImportPyModulePluginsRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_IMPORT_PY_MODULE_PLUGINS)))
          .addMethod(
            getImportPyPackagePluginsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ImportPyPackagePluginsRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_IMPORT_PY_PACKAGE_PLUGINS)))
          .addMethod(
            getConfigureMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProcessPluginRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_CONFIGURE)))
          .addMethod(
            getTasksMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProcessPluginRequest,
                io.paddle.plugin.interop.GetTasksResponse>(
                  this, METHODID_TASKS)))
          .addMethod(
            getInitializeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProcessTaskRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_INITIALIZE)))
          .addMethod(
            getActMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProcessTaskRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ACT)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * Paddle's plugins service definition.
   * </pre>
   */
  public static final class PluginsStub extends io.grpc.stub.AbstractAsyncStub<PluginsStub> {
    private PluginsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PluginsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PluginsStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Initializes project stub on Python Plugins Server.
     * </pre>
     */
    public void initializeProjectStub(io.paddle.plugin.interop.InitializeProjectRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInitializeProjectStubMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Imports plugins from specified local python modules into Python Plugins Server.
     * </pre>
     */
    public void importPyModulePlugins(io.paddle.plugin.interop.ImportPyModulePluginsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getImportPyModulePluginsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Imports plugins from specified python packages into Python Plugins Server.
     * </pre>
     */
    public void importPyPackagePlugins(io.paddle.plugin.interop.ImportPyPackagePluginsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getImportPyPackagePluginsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Executes configure of specified project with specified plugin.
     * </pre>
     */
    public void configure(io.paddle.plugin.interop.ProcessPluginRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfigureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Requests info about all tasks of specified plugin.
     * </pre>
     */
    public void tasks(io.paddle.plugin.interop.ProcessPluginRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.GetTasksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTasksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Initializes specified task with respect to specified project.
     * </pre>
     */
    public void initialize(io.paddle.plugin.interop.ProcessTaskRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInitializeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Executes action of specified task with respect to specified project.
     * </pre>
     */
    public void act(io.paddle.plugin.interop.ProcessTaskRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getActMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   * Paddle's plugins service definition.
   * </pre>
   */
  public static final class PluginsBlockingStub extends io.grpc.stub.AbstractBlockingStub<PluginsBlockingStub> {
    private PluginsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PluginsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PluginsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Initializes project stub on Python Plugins Server.
     * </pre>
     */
    public com.google.protobuf.Empty initializeProjectStub(io.paddle.plugin.interop.InitializeProjectRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInitializeProjectStubMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Imports plugins from specified local python modules into Python Plugins Server.
     * </pre>
     */
    public com.google.protobuf.Empty importPyModulePlugins(io.paddle.plugin.interop.ImportPyModulePluginsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getImportPyModulePluginsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Imports plugins from specified python packages into Python Plugins Server.
     * </pre>
     */
    public com.google.protobuf.Empty importPyPackagePlugins(io.paddle.plugin.interop.ImportPyPackagePluginsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getImportPyPackagePluginsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Executes configure of specified project with specified plugin.
     * </pre>
     */
    public com.google.protobuf.Empty configure(io.paddle.plugin.interop.ProcessPluginRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfigureMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Requests info about all tasks of specified plugin.
     * </pre>
     */
    public io.paddle.plugin.interop.GetTasksResponse tasks(io.paddle.plugin.interop.ProcessPluginRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTasksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Initializes specified task with respect to specified project.
     * </pre>
     */
    public com.google.protobuf.Empty initialize(io.paddle.plugin.interop.ProcessTaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInitializeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Executes action of specified task with respect to specified project.
     * </pre>
     */
    public com.google.protobuf.Empty act(io.paddle.plugin.interop.ProcessTaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getActMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * Paddle's plugins service definition.
   * </pre>
   */
  public static final class PluginsFutureStub extends io.grpc.stub.AbstractFutureStub<PluginsFutureStub> {
    private PluginsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PluginsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PluginsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Initializes project stub on Python Plugins Server.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> initializeProjectStub(
        io.paddle.plugin.interop.InitializeProjectRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInitializeProjectStubMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Imports plugins from specified local python modules into Python Plugins Server.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> importPyModulePlugins(
        io.paddle.plugin.interop.ImportPyModulePluginsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getImportPyModulePluginsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Imports plugins from specified python packages into Python Plugins Server.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> importPyPackagePlugins(
        io.paddle.plugin.interop.ImportPyPackagePluginsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getImportPyPackagePluginsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Executes configure of specified project with specified plugin.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> configure(
        io.paddle.plugin.interop.ProcessPluginRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfigureMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Requests info about all tasks of specified plugin.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.paddle.plugin.interop.GetTasksResponse> tasks(
        io.paddle.plugin.interop.ProcessPluginRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTasksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Initializes specified task with respect to specified project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> initialize(
        io.paddle.plugin.interop.ProcessTaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInitializeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Executes action of specified task with respect to specified project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> act(
        io.paddle.plugin.interop.ProcessTaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getActMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INITIALIZE_PROJECT_STUB = 0;
  private static final int METHODID_IMPORT_PY_MODULE_PLUGINS = 1;
  private static final int METHODID_IMPORT_PY_PACKAGE_PLUGINS = 2;
  private static final int METHODID_CONFIGURE = 3;
  private static final int METHODID_TASKS = 4;
  private static final int METHODID_INITIALIZE = 5;
  private static final int METHODID_ACT = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PluginsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PluginsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INITIALIZE_PROJECT_STUB:
          serviceImpl.initializeProjectStub((io.paddle.plugin.interop.InitializeProjectRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_IMPORT_PY_MODULE_PLUGINS:
          serviceImpl.importPyModulePlugins((io.paddle.plugin.interop.ImportPyModulePluginsRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_IMPORT_PY_PACKAGE_PLUGINS:
          serviceImpl.importPyPackagePlugins((io.paddle.plugin.interop.ImportPyPackagePluginsRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_CONFIGURE:
          serviceImpl.configure((io.paddle.plugin.interop.ProcessPluginRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_TASKS:
          serviceImpl.tasks((io.paddle.plugin.interop.ProcessPluginRequest) request,
              (io.grpc.stub.StreamObserver<io.paddle.plugin.interop.GetTasksResponse>) responseObserver);
          break;
        case METHODID_INITIALIZE:
          serviceImpl.initialize((io.paddle.plugin.interop.ProcessTaskRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_ACT:
          serviceImpl.act((io.paddle.plugin.interop.ProcessTaskRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PluginsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PluginsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.paddle.plugin.interop.PluginsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Plugins");
    }
  }

  private static final class PluginsFileDescriptorSupplier
      extends PluginsBaseDescriptorSupplier {
    PluginsFileDescriptorSupplier() {}
  }

  private static final class PluginsMethodDescriptorSupplier
      extends PluginsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PluginsMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PluginsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PluginsFileDescriptorSupplier())
              .addMethod(getInitializeProjectStubMethod())
              .addMethod(getImportPyModulePluginsMethod())
              .addMethod(getImportPyPackagePluginsMethod())
              .addMethod(getConfigureMethod())
              .addMethod(getTasksMethod())
              .addMethod(getInitializeMethod())
              .addMethod(getActMethod())
              .build();
        }
      }
    }
    return result;
  }
}
