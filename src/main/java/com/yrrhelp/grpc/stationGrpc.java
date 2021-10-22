package com.yrrhelp.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: station.proto")
public final class stationGrpc {

  private stationGrpc() {}

  public static final String SERVICE_NAME = "station";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.yrrhelp.grpc.Station.GetStationDataRequest,
      com.yrrhelp.grpc.Station.APIResponse> getGetStationDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getStationData",
      requestType = com.yrrhelp.grpc.Station.GetStationDataRequest.class,
      responseType = com.yrrhelp.grpc.Station.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.yrrhelp.grpc.Station.GetStationDataRequest,
      com.yrrhelp.grpc.Station.APIResponse> getGetStationDataMethod() {
    io.grpc.MethodDescriptor<com.yrrhelp.grpc.Station.GetStationDataRequest, com.yrrhelp.grpc.Station.APIResponse> getGetStationDataMethod;
    if ((getGetStationDataMethod = stationGrpc.getGetStationDataMethod) == null) {
      synchronized (stationGrpc.class) {
        if ((getGetStationDataMethod = stationGrpc.getGetStationDataMethod) == null) {
          stationGrpc.getGetStationDataMethod = getGetStationDataMethod = 
              io.grpc.MethodDescriptor.<com.yrrhelp.grpc.Station.GetStationDataRequest, com.yrrhelp.grpc.Station.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "station", "getStationData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yrrhelp.grpc.Station.GetStationDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yrrhelp.grpc.Station.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new stationMethodDescriptorSupplier("getStationData"))
                  .build();
          }
        }
     }
     return getGetStationDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static stationStub newStub(io.grpc.Channel channel) {
    return new stationStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static stationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new stationBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static stationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new stationFutureStub(channel);
  }

  /**
   */
  public static abstract class stationImplBase implements io.grpc.BindableService {

    /**
     */
    public void getStationData(com.yrrhelp.grpc.Station.GetStationDataRequest request,
        io.grpc.stub.StreamObserver<com.yrrhelp.grpc.Station.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStationDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetStationDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.yrrhelp.grpc.Station.GetStationDataRequest,
                com.yrrhelp.grpc.Station.APIResponse>(
                  this, METHODID_GET_STATION_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class stationStub extends io.grpc.stub.AbstractStub<stationStub> {
    private stationStub(io.grpc.Channel channel) {
      super(channel);
    }

    private stationStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected stationStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new stationStub(channel, callOptions);
    }

    /**
     */
    public void getStationData(com.yrrhelp.grpc.Station.GetStationDataRequest request,
        io.grpc.stub.StreamObserver<com.yrrhelp.grpc.Station.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetStationDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class stationBlockingStub extends io.grpc.stub.AbstractStub<stationBlockingStub> {
    private stationBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private stationBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected stationBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new stationBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.yrrhelp.grpc.Station.APIResponse getStationData(com.yrrhelp.grpc.Station.GetStationDataRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetStationDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class stationFutureStub extends io.grpc.stub.AbstractStub<stationFutureStub> {
    private stationFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private stationFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected stationFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new stationFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.yrrhelp.grpc.Station.APIResponse> getStationData(
        com.yrrhelp.grpc.Station.GetStationDataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetStationDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_STATION_DATA = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final stationImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(stationImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STATION_DATA:
          serviceImpl.getStationData((com.yrrhelp.grpc.Station.GetStationDataRequest) request,
              (io.grpc.stub.StreamObserver<com.yrrhelp.grpc.Station.APIResponse>) responseObserver);
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

  private static abstract class stationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    stationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.yrrhelp.grpc.Station.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("station");
    }
  }

  private static final class stationFileDescriptorSupplier
      extends stationBaseDescriptorSupplier {
    stationFileDescriptorSupplier() {}
  }

  private static final class stationMethodDescriptorSupplier
      extends stationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    stationMethodDescriptorSupplier(String methodName) {
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
      synchronized (stationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new stationFileDescriptorSupplier())
              .addMethod(getGetStationDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
