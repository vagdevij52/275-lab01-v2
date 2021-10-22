package com.message.proto;

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
    comments = "Source: pollserverqueue.proto")
public final class PollServerProcessorGrpc {

  private PollServerProcessorGrpc() {}

  public static final String SERVICE_NAME = "PollServerProcessor";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.message.proto.Pollserverqueue.PollServerRequest,
      com.message.proto.Pollserverqueue.PollServerReply> getPollMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "poll",
      requestType = com.message.proto.Pollserverqueue.PollServerRequest.class,
      responseType = com.message.proto.Pollserverqueue.PollServerReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.message.proto.Pollserverqueue.PollServerRequest,
      com.message.proto.Pollserverqueue.PollServerReply> getPollMethod() {
    io.grpc.MethodDescriptor<com.message.proto.Pollserverqueue.PollServerRequest, com.message.proto.Pollserverqueue.PollServerReply> getPollMethod;
    if ((getPollMethod = PollServerProcessorGrpc.getPollMethod) == null) {
      synchronized (PollServerProcessorGrpc.class) {
        if ((getPollMethod = PollServerProcessorGrpc.getPollMethod) == null) {
          PollServerProcessorGrpc.getPollMethod = getPollMethod = 
              io.grpc.MethodDescriptor.<com.message.proto.Pollserverqueue.PollServerRequest, com.message.proto.Pollserverqueue.PollServerReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "PollServerProcessor", "poll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.message.proto.Pollserverqueue.PollServerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.message.proto.Pollserverqueue.PollServerReply.getDefaultInstance()))
                  .setSchemaDescriptor(new PollServerProcessorMethodDescriptorSupplier("poll"))
                  .build();
          }
        }
     }
     return getPollMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PollServerProcessorStub newStub(io.grpc.Channel channel) {
    return new PollServerProcessorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PollServerProcessorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PollServerProcessorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PollServerProcessorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PollServerProcessorFutureStub(channel);
  }

  /**
   */
  public static abstract class PollServerProcessorImplBase implements io.grpc.BindableService {

    /**
     */
    public void poll(com.message.proto.Pollserverqueue.PollServerRequest request,
        io.grpc.stub.StreamObserver<com.message.proto.Pollserverqueue.PollServerReply> responseObserver) {
      asyncUnimplementedUnaryCall(getPollMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPollMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.message.proto.Pollserverqueue.PollServerRequest,
                com.message.proto.Pollserverqueue.PollServerReply>(
                  this, METHODID_POLL)))
          .build();
    }
  }

  /**
   */
  public static final class PollServerProcessorStub extends io.grpc.stub.AbstractStub<PollServerProcessorStub> {
    private PollServerProcessorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PollServerProcessorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PollServerProcessorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PollServerProcessorStub(channel, callOptions);
    }

    /**
     */
    public void poll(com.message.proto.Pollserverqueue.PollServerRequest request,
        io.grpc.stub.StreamObserver<com.message.proto.Pollserverqueue.PollServerReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPollMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PollServerProcessorBlockingStub extends io.grpc.stub.AbstractStub<PollServerProcessorBlockingStub> {
    private PollServerProcessorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PollServerProcessorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PollServerProcessorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PollServerProcessorBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.message.proto.Pollserverqueue.PollServerReply poll(com.message.proto.Pollserverqueue.PollServerRequest request) {
      return blockingUnaryCall(
          getChannel(), getPollMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PollServerProcessorFutureStub extends io.grpc.stub.AbstractStub<PollServerProcessorFutureStub> {
    private PollServerProcessorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PollServerProcessorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PollServerProcessorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PollServerProcessorFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.message.proto.Pollserverqueue.PollServerReply> poll(
        com.message.proto.Pollserverqueue.PollServerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPollMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_POLL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PollServerProcessorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PollServerProcessorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_POLL:
          serviceImpl.poll((com.message.proto.Pollserverqueue.PollServerRequest) request,
              (io.grpc.stub.StreamObserver<com.message.proto.Pollserverqueue.PollServerReply>) responseObserver);
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

  private static abstract class PollServerProcessorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PollServerProcessorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.message.proto.Pollserverqueue.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PollServerProcessor");
    }
  }

  private static final class PollServerProcessorFileDescriptorSupplier
      extends PollServerProcessorBaseDescriptorSupplier {
    PollServerProcessorFileDescriptorSupplier() {}
  }

  private static final class PollServerProcessorMethodDescriptorSupplier
      extends PollServerProcessorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PollServerProcessorMethodDescriptorSupplier(String methodName) {
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
      synchronized (PollServerProcessorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PollServerProcessorFileDescriptorSupplier())
              .addMethod(getPollMethod())
              .build();
        }
      }
    }
    return result;
  }
}
