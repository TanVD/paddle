package io.paddle.plugin.interop;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Paddle's project API service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: project.proto")
public final class ProjectGrpc {

  private ProjectGrpc() {}

  public static final String SERVICE_NAME = "io.paddle.plugin.interop.Project";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.PrintRequest,
      com.google.protobuf.Empty> getPrintMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrintMessage",
      requestType = io.paddle.plugin.interop.PrintRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.PrintRequest,
      com.google.protobuf.Empty> getPrintMessageMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.PrintRequest, com.google.protobuf.Empty> getPrintMessageMethod;
    if ((getPrintMessageMethod = ProjectGrpc.getPrintMessageMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getPrintMessageMethod = ProjectGrpc.getPrintMessageMethod) == null) {
          ProjectGrpc.getPrintMessageMethod = getPrintMessageMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.PrintRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrintMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.PrintRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("PrintMessage"))
              .build();
        }
      }
    }
    return getPrintMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ExecuteCommandRequest,
      com.google.protobuf.Empty> getExecuteCommandMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecuteCommand",
      requestType = io.paddle.plugin.interop.ExecuteCommandRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ExecuteCommandRequest,
      com.google.protobuf.Empty> getExecuteCommandMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ExecuteCommandRequest, com.google.protobuf.Empty> getExecuteCommandMethod;
    if ((getExecuteCommandMethod = ProjectGrpc.getExecuteCommandMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getExecuteCommandMethod = ProjectGrpc.getExecuteCommandMethod) == null) {
          ProjectGrpc.getExecuteCommandMethod = getExecuteCommandMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ExecuteCommandRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecuteCommand"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ExecuteCommandRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("ExecuteCommand"))
              .build();
        }
      }
    }
    return getExecuteCommandMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.WorkingDir> getGetWorkingDirectoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetWorkingDirectory",
      requestType = io.paddle.plugin.interop.ProjectInfoRequest.class,
      responseType = io.paddle.plugin.interop.WorkingDir.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.WorkingDir> getGetWorkingDirectoryMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.WorkingDir> getGetWorkingDirectoryMethod;
    if ((getGetWorkingDirectoryMethod = ProjectGrpc.getGetWorkingDirectoryMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getGetWorkingDirectoryMethod = ProjectGrpc.getGetWorkingDirectoryMethod) == null) {
          ProjectGrpc.getGetWorkingDirectoryMethod = getGetWorkingDirectoryMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.WorkingDir>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetWorkingDirectory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProjectInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.WorkingDir.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("GetWorkingDirectory"))
              .build();
        }
      }
    }
    return getGetWorkingDirectoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.Description> getGetDescriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDescription",
      requestType = io.paddle.plugin.interop.ProjectInfoRequest.class,
      responseType = io.paddle.plugin.interop.Description.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.Description> getGetDescriptionMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.Description> getGetDescriptionMethod;
    if ((getGetDescriptionMethod = ProjectGrpc.getGetDescriptionMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getGetDescriptionMethod = ProjectGrpc.getGetDescriptionMethod) == null) {
          ProjectGrpc.getGetDescriptionMethod = getGetDescriptionMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.Description>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDescription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProjectInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.Description.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("GetDescription"))
              .build();
        }
      }
    }
    return getGetDescriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.Roots> getGetRootsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRoots",
      requestType = io.paddle.plugin.interop.ProjectInfoRequest.class,
      responseType = io.paddle.plugin.interop.Roots.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.Roots> getGetRootsMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.Roots> getGetRootsMethod;
    if ((getGetRootsMethod = ProjectGrpc.getGetRootsMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getGetRootsMethod = ProjectGrpc.getGetRootsMethod) == null) {
          ProjectGrpc.getGetRootsMethod = getGetRootsMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.Roots>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRoots"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProjectInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.Roots.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("GetRoots"))
              .build();
        }
      }
    }
    return getGetRootsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest,
      com.google.protobuf.Empty> getAddSourcesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddSources",
      requestType = io.paddle.plugin.interop.AddPathsRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest,
      com.google.protobuf.Empty> getAddSourcesMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest, com.google.protobuf.Empty> getAddSourcesMethod;
    if ((getAddSourcesMethod = ProjectGrpc.getAddSourcesMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getAddSourcesMethod = ProjectGrpc.getAddSourcesMethod) == null) {
          ProjectGrpc.getAddSourcesMethod = getAddSourcesMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.AddPathsRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddSources"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.AddPathsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("AddSources"))
              .build();
        }
      }
    }
    return getAddSourcesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest,
      com.google.protobuf.Empty> getAddTestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddTests",
      requestType = io.paddle.plugin.interop.AddPathsRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest,
      com.google.protobuf.Empty> getAddTestsMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest, com.google.protobuf.Empty> getAddTestsMethod;
    if ((getAddTestsMethod = ProjectGrpc.getAddTestsMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getAddTestsMethod = ProjectGrpc.getAddTestsMethod) == null) {
          ProjectGrpc.getAddTestsMethod = getAddTestsMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.AddPathsRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddTests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.AddPathsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("AddTests"))
              .build();
        }
      }
    }
    return getAddTestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest,
      com.google.protobuf.Empty> getAddResourcesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddResources",
      requestType = io.paddle.plugin.interop.AddPathsRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest,
      com.google.protobuf.Empty> getAddResourcesMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest, com.google.protobuf.Empty> getAddResourcesMethod;
    if ((getAddResourcesMethod = ProjectGrpc.getAddResourcesMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getAddResourcesMethod = ProjectGrpc.getAddResourcesMethod) == null) {
          ProjectGrpc.getAddResourcesMethod = getAddResourcesMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.AddPathsRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddResources"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.AddPathsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("AddResources"))
              .build();
        }
      }
    }
    return getAddResourcesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.Tasks> getGetTasksNamesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTasksNames",
      requestType = io.paddle.plugin.interop.ProjectInfoRequest.class,
      responseType = io.paddle.plugin.interop.Tasks.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.Tasks> getGetTasksNamesMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.Tasks> getGetTasksNamesMethod;
    if ((getGetTasksNamesMethod = ProjectGrpc.getGetTasksNamesMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getGetTasksNamesMethod = ProjectGrpc.getGetTasksNamesMethod) == null) {
          ProjectGrpc.getGetTasksNamesMethod = getGetTasksNamesMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.Tasks>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTasksNames"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProjectInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.Tasks.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("GetTasksNames"))
              .build();
        }
      }
    }
    return getGetTasksNamesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest,
      com.google.protobuf.Empty> getRunTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RunTask",
      requestType = io.paddle.plugin.interop.ProcessTaskRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest,
      com.google.protobuf.Empty> getRunTaskMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProcessTaskRequest, com.google.protobuf.Empty> getRunTaskMethod;
    if ((getRunTaskMethod = ProjectGrpc.getRunTaskMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getRunTaskMethod = ProjectGrpc.getRunTaskMethod) == null) {
          ProjectGrpc.getRunTaskMethod = getRunTaskMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProcessTaskRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RunTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProcessTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("RunTask"))
              .build();
        }
      }
    }
    return getRunTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest,
      com.google.protobuf.Empty> getAddCleanLocationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddCleanLocation",
      requestType = io.paddle.plugin.interop.AddPathsRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest,
      com.google.protobuf.Empty> getAddCleanLocationMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.AddPathsRequest, com.google.protobuf.Empty> getAddCleanLocationMethod;
    if ((getAddCleanLocationMethod = ProjectGrpc.getAddCleanLocationMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getAddCleanLocationMethod = ProjectGrpc.getAddCleanLocationMethod) == null) {
          ProjectGrpc.getAddCleanLocationMethod = getAddCleanLocationMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.AddPathsRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddCleanLocation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.AddPathsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("AddCleanLocation"))
              .build();
        }
      }
    }
    return getAddCleanLocationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.CompositeSpecNode> getGetConfigurationSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetConfigurationSpecification",
      requestType = io.paddle.plugin.interop.ProjectInfoRequest.class,
      responseType = io.paddle.plugin.interop.CompositeSpecNode.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest,
      io.paddle.plugin.interop.CompositeSpecNode> getGetConfigurationSpecificationMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.CompositeSpecNode> getGetConfigurationSpecificationMethod;
    if ((getGetConfigurationSpecificationMethod = ProjectGrpc.getGetConfigurationSpecificationMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getGetConfigurationSpecificationMethod = ProjectGrpc.getGetConfigurationSpecificationMethod) == null) {
          ProjectGrpc.getGetConfigurationSpecificationMethod = getGetConfigurationSpecificationMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.ProjectInfoRequest, io.paddle.plugin.interop.CompositeSpecNode>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetConfigurationSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.ProjectInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.CompositeSpecNode.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("GetConfigurationSpecification"))
              .build();
        }
      }
    }
    return getGetConfigurationSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.paddle.plugin.interop.UpdateConfigSpecRequest,
      com.google.protobuf.Empty> getUpdateConfigurationSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateConfigurationSpecification",
      requestType = io.paddle.plugin.interop.UpdateConfigSpecRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.paddle.plugin.interop.UpdateConfigSpecRequest,
      com.google.protobuf.Empty> getUpdateConfigurationSpecificationMethod() {
    io.grpc.MethodDescriptor<io.paddle.plugin.interop.UpdateConfigSpecRequest, com.google.protobuf.Empty> getUpdateConfigurationSpecificationMethod;
    if ((getUpdateConfigurationSpecificationMethod = ProjectGrpc.getUpdateConfigurationSpecificationMethod) == null) {
      synchronized (ProjectGrpc.class) {
        if ((getUpdateConfigurationSpecificationMethod = ProjectGrpc.getUpdateConfigurationSpecificationMethod) == null) {
          ProjectGrpc.getUpdateConfigurationSpecificationMethod = getUpdateConfigurationSpecificationMethod =
              io.grpc.MethodDescriptor.<io.paddle.plugin.interop.UpdateConfigSpecRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateConfigurationSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.paddle.plugin.interop.UpdateConfigSpecRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ProjectMethodDescriptorSupplier("UpdateConfigurationSpecification"))
              .build();
        }
      }
    }
    return getUpdateConfigurationSpecificationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProjectStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProjectStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProjectStub>() {
        @java.lang.Override
        public ProjectStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProjectStub(channel, callOptions);
        }
      };
    return ProjectStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProjectBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProjectBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProjectBlockingStub>() {
        @java.lang.Override
        public ProjectBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProjectBlockingStub(channel, callOptions);
        }
      };
    return ProjectBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProjectFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProjectFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProjectFutureStub>() {
        @java.lang.Override
        public ProjectFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProjectFutureStub(channel, callOptions);
        }
      };
    return ProjectFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Paddle's project API service definition.
   * </pre>
   */
  public static abstract class ProjectImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * Prints message to the terminal associated with specified project.
     * </pre>
     */
    public void printMessage(io.paddle.plugin.interop.PrintRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPrintMessageMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Executes specified command with passed arguments.
     * </pre>
     */
    public void executeCommand(io.paddle.plugin.interop.ExecuteCommandRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteCommandMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Returns working directory path for specified project.
     * </pre>
     */
    public void getWorkingDirectory(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.WorkingDir> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetWorkingDirectoryMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Returns description of specified project.
     * </pre>
     */
    public void getDescription(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Description> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDescriptionMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Returns triple consists of list of file paths to project source code,
     * list of paths to project test files and list of paths to project resource files.
     * </pre>
     */
    public void getRoots(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Roots> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRootsMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's sources roots.
     * </pre>
     */
    public void addSources(io.paddle.plugin.interop.AddPathsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddSourcesMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's tests roots.
     * </pre>
     */
    public void addTests(io.paddle.plugin.interop.AddPathsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddTestsMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's resources roots.
     * </pre>
     */
    public void addResources(io.paddle.plugin.interop.AddPathsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddResourcesMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Returns list of tasks names associates with specified project.
     * </pre>
     */
    public void getTasksNames(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Tasks> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTasksNamesMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Run task by id associate with specfied project.
     * </pre>
     */
    public void runTask(io.paddle.plugin.interop.ProcessTaskRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRunTaskMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Add specified paths to clean task's location list.
     * </pre>
     */
    public void addCleanLocation(io.paddle.plugin.interop.AddPathsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddCleanLocationMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Returns configuration specification of specified project.
     * </pre>
     */
    public void getConfigurationSpecification(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.CompositeSpecNode> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetConfigurationSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Updates configuration specification of specified project.
     * </pre>
     */
    public void updateConfigurationSpecification(io.paddle.plugin.interop.UpdateConfigSpecRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateConfigurationSpecificationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPrintMessageMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.PrintRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_PRINT_MESSAGE)))
          .addMethod(
            getExecuteCommandMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ExecuteCommandRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_EXECUTE_COMMAND)))
          .addMethod(
            getGetWorkingDirectoryMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProjectInfoRequest,
                io.paddle.plugin.interop.WorkingDir>(
                  this, METHODID_GET_WORKING_DIRECTORY)))
          .addMethod(
            getGetDescriptionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProjectInfoRequest,
                io.paddle.plugin.interop.Description>(
                  this, METHODID_GET_DESCRIPTION)))
          .addMethod(
            getGetRootsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProjectInfoRequest,
                io.paddle.plugin.interop.Roots>(
                  this, METHODID_GET_ROOTS)))
          .addMethod(
            getAddSourcesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.AddPathsRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ADD_SOURCES)))
          .addMethod(
            getAddTestsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.AddPathsRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ADD_TESTS)))
          .addMethod(
            getAddResourcesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.AddPathsRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ADD_RESOURCES)))
          .addMethod(
            getGetTasksNamesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProjectInfoRequest,
                io.paddle.plugin.interop.Tasks>(
                  this, METHODID_GET_TASKS_NAMES)))
          .addMethod(
            getRunTaskMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProcessTaskRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_RUN_TASK)))
          .addMethod(
            getAddCleanLocationMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.AddPathsRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ADD_CLEAN_LOCATION)))
          .addMethod(
            getGetConfigurationSpecificationMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.ProjectInfoRequest,
                io.paddle.plugin.interop.CompositeSpecNode>(
                  this, METHODID_GET_CONFIGURATION_SPECIFICATION)))
          .addMethod(
            getUpdateConfigurationSpecificationMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.paddle.plugin.interop.UpdateConfigSpecRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_UPDATE_CONFIGURATION_SPECIFICATION)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * Paddle's project API service definition.
   * </pre>
   */
  public static final class ProjectStub extends io.grpc.stub.AbstractAsyncStub<ProjectStub> {
    private ProjectStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProjectStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProjectStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Prints message to the terminal associated with specified project.
     * </pre>
     */
    public void printMessage(io.paddle.plugin.interop.PrintRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPrintMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Executes specified command with passed arguments.
     * </pre>
     */
    public void executeCommand(io.paddle.plugin.interop.ExecuteCommandRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteCommandMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Returns working directory path for specified project.
     * </pre>
     */
    public void getWorkingDirectory(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.WorkingDir> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetWorkingDirectoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Returns description of specified project.
     * </pre>
     */
    public void getDescription(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Description> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDescriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Returns triple consists of list of file paths to project source code,
     * list of paths to project test files and list of paths to project resource files.
     * </pre>
     */
    public void getRoots(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Roots> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetRootsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's sources roots.
     * </pre>
     */
    public void addSources(io.paddle.plugin.interop.AddPathsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddSourcesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's tests roots.
     * </pre>
     */
    public void addTests(io.paddle.plugin.interop.AddPathsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddTestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's resources roots.
     * </pre>
     */
    public void addResources(io.paddle.plugin.interop.AddPathsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddResourcesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Returns list of tasks names associates with specified project.
     * </pre>
     */
    public void getTasksNames(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Tasks> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTasksNamesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Run task by id associate with specfied project.
     * </pre>
     */
    public void runTask(io.paddle.plugin.interop.ProcessTaskRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRunTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Add specified paths to clean task's location list.
     * </pre>
     */
    public void addCleanLocation(io.paddle.plugin.interop.AddPathsRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddCleanLocationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Returns configuration specification of specified project.
     * </pre>
     */
    public void getConfigurationSpecification(io.paddle.plugin.interop.ProjectInfoRequest request,
        io.grpc.stub.StreamObserver<io.paddle.plugin.interop.CompositeSpecNode> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetConfigurationSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Updates configuration specification of specified project.
     * </pre>
     */
    public void updateConfigurationSpecification(io.paddle.plugin.interop.UpdateConfigSpecRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateConfigurationSpecificationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   * Paddle's project API service definition.
   * </pre>
   */
  public static final class ProjectBlockingStub extends io.grpc.stub.AbstractBlockingStub<ProjectBlockingStub> {
    private ProjectBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProjectBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProjectBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Prints message to the terminal associated with specified project.
     * </pre>
     */
    public com.google.protobuf.Empty printMessage(io.paddle.plugin.interop.PrintRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrintMessageMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Executes specified command with passed arguments.
     * </pre>
     */
    public com.google.protobuf.Empty executeCommand(io.paddle.plugin.interop.ExecuteCommandRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteCommandMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Returns working directory path for specified project.
     * </pre>
     */
    public io.paddle.plugin.interop.WorkingDir getWorkingDirectory(io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetWorkingDirectoryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Returns description of specified project.
     * </pre>
     */
    public io.paddle.plugin.interop.Description getDescription(io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDescriptionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Returns triple consists of list of file paths to project source code,
     * list of paths to project test files and list of paths to project resource files.
     * </pre>
     */
    public io.paddle.plugin.interop.Roots getRoots(io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetRootsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's sources roots.
     * </pre>
     */
    public com.google.protobuf.Empty addSources(io.paddle.plugin.interop.AddPathsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddSourcesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's tests roots.
     * </pre>
     */
    public com.google.protobuf.Empty addTests(io.paddle.plugin.interop.AddPathsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddTestsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's resources roots.
     * </pre>
     */
    public com.google.protobuf.Empty addResources(io.paddle.plugin.interop.AddPathsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddResourcesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Returns list of tasks names associates with specified project.
     * </pre>
     */
    public io.paddle.plugin.interop.Tasks getTasksNames(io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTasksNamesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Run task by id associate with specfied project.
     * </pre>
     */
    public com.google.protobuf.Empty runTask(io.paddle.plugin.interop.ProcessTaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRunTaskMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Add specified paths to clean task's location list.
     * </pre>
     */
    public com.google.protobuf.Empty addCleanLocation(io.paddle.plugin.interop.AddPathsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddCleanLocationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Returns configuration specification of specified project.
     * </pre>
     */
    public io.paddle.plugin.interop.CompositeSpecNode getConfigurationSpecification(io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetConfigurationSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Updates configuration specification of specified project.
     * </pre>
     */
    public com.google.protobuf.Empty updateConfigurationSpecification(io.paddle.plugin.interop.UpdateConfigSpecRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateConfigurationSpecificationMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * Paddle's project API service definition.
   * </pre>
   */
  public static final class ProjectFutureStub extends io.grpc.stub.AbstractFutureStub<ProjectFutureStub> {
    private ProjectFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProjectFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProjectFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Prints message to the terminal associated with specified project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> printMessage(
        io.paddle.plugin.interop.PrintRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPrintMessageMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Executes specified command with passed arguments.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> executeCommand(
        io.paddle.plugin.interop.ExecuteCommandRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecuteCommandMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Returns working directory path for specified project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.paddle.plugin.interop.WorkingDir> getWorkingDirectory(
        io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetWorkingDirectoryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Returns description of specified project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.paddle.plugin.interop.Description> getDescription(
        io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDescriptionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Returns triple consists of list of file paths to project source code,
     * list of paths to project test files and list of paths to project resource files.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.paddle.plugin.interop.Roots> getRoots(
        io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetRootsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's sources roots.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> addSources(
        io.paddle.plugin.interop.AddPathsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddSourcesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's tests roots.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> addTests(
        io.paddle.plugin.interop.AddPathsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddTestsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Add paths to specified project's resources roots.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> addResources(
        io.paddle.plugin.interop.AddPathsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddResourcesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Returns list of tasks names associates with specified project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.paddle.plugin.interop.Tasks> getTasksNames(
        io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTasksNamesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Run task by id associate with specfied project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> runTask(
        io.paddle.plugin.interop.ProcessTaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRunTaskMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Add specified paths to clean task's location list.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> addCleanLocation(
        io.paddle.plugin.interop.AddPathsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddCleanLocationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Returns configuration specification of specified project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.paddle.plugin.interop.CompositeSpecNode> getConfigurationSpecification(
        io.paddle.plugin.interop.ProjectInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetConfigurationSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Updates configuration specification of specified project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateConfigurationSpecification(
        io.paddle.plugin.interop.UpdateConfigSpecRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateConfigurationSpecificationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PRINT_MESSAGE = 0;
  private static final int METHODID_EXECUTE_COMMAND = 1;
  private static final int METHODID_GET_WORKING_DIRECTORY = 2;
  private static final int METHODID_GET_DESCRIPTION = 3;
  private static final int METHODID_GET_ROOTS = 4;
  private static final int METHODID_ADD_SOURCES = 5;
  private static final int METHODID_ADD_TESTS = 6;
  private static final int METHODID_ADD_RESOURCES = 7;
  private static final int METHODID_GET_TASKS_NAMES = 8;
  private static final int METHODID_RUN_TASK = 9;
  private static final int METHODID_ADD_CLEAN_LOCATION = 10;
  private static final int METHODID_GET_CONFIGURATION_SPECIFICATION = 11;
  private static final int METHODID_UPDATE_CONFIGURATION_SPECIFICATION = 12;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProjectImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProjectImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PRINT_MESSAGE:
          serviceImpl.printMessage((io.paddle.plugin.interop.PrintRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_EXECUTE_COMMAND:
          serviceImpl.executeCommand((io.paddle.plugin.interop.ExecuteCommandRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_WORKING_DIRECTORY:
          serviceImpl.getWorkingDirectory((io.paddle.plugin.interop.ProjectInfoRequest) request,
              (io.grpc.stub.StreamObserver<io.paddle.plugin.interop.WorkingDir>) responseObserver);
          break;
        case METHODID_GET_DESCRIPTION:
          serviceImpl.getDescription((io.paddle.plugin.interop.ProjectInfoRequest) request,
              (io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Description>) responseObserver);
          break;
        case METHODID_GET_ROOTS:
          serviceImpl.getRoots((io.paddle.plugin.interop.ProjectInfoRequest) request,
              (io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Roots>) responseObserver);
          break;
        case METHODID_ADD_SOURCES:
          serviceImpl.addSources((io.paddle.plugin.interop.AddPathsRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_ADD_TESTS:
          serviceImpl.addTests((io.paddle.plugin.interop.AddPathsRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_ADD_RESOURCES:
          serviceImpl.addResources((io.paddle.plugin.interop.AddPathsRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_TASKS_NAMES:
          serviceImpl.getTasksNames((io.paddle.plugin.interop.ProjectInfoRequest) request,
              (io.grpc.stub.StreamObserver<io.paddle.plugin.interop.Tasks>) responseObserver);
          break;
        case METHODID_RUN_TASK:
          serviceImpl.runTask((io.paddle.plugin.interop.ProcessTaskRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_ADD_CLEAN_LOCATION:
          serviceImpl.addCleanLocation((io.paddle.plugin.interop.AddPathsRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_CONFIGURATION_SPECIFICATION:
          serviceImpl.getConfigurationSpecification((io.paddle.plugin.interop.ProjectInfoRequest) request,
              (io.grpc.stub.StreamObserver<io.paddle.plugin.interop.CompositeSpecNode>) responseObserver);
          break;
        case METHODID_UPDATE_CONFIGURATION_SPECIFICATION:
          serviceImpl.updateConfigurationSpecification((io.paddle.plugin.interop.UpdateConfigSpecRequest) request,
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

  private static abstract class ProjectBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProjectBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.paddle.plugin.interop.ProjectOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Project");
    }
  }

  private static final class ProjectFileDescriptorSupplier
      extends ProjectBaseDescriptorSupplier {
    ProjectFileDescriptorSupplier() {}
  }

  private static final class ProjectMethodDescriptorSupplier
      extends ProjectBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProjectMethodDescriptorSupplier(String methodName) {
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
      synchronized (ProjectGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProjectFileDescriptorSupplier())
              .addMethod(getPrintMessageMethod())
              .addMethod(getExecuteCommandMethod())
              .addMethod(getGetWorkingDirectoryMethod())
              .addMethod(getGetDescriptionMethod())
              .addMethod(getGetRootsMethod())
              .addMethod(getAddSourcesMethod())
              .addMethod(getAddTestsMethod())
              .addMethod(getAddResourcesMethod())
              .addMethod(getGetTasksNamesMethod())
              .addMethod(getRunTaskMethod())
              .addMethod(getAddCleanLocationMethod())
              .addMethod(getGetConfigurationSpecificationMethod())
              .addMethod(getUpdateConfigurationSpecificationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
