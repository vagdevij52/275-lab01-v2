package client;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import com.yrrhelp.grpc.stationGrpc;
import com.message.proto.PollServerProcessorGrpc;
import com.message.proto.PollServerProcessorGrpc.PollServerProcessorBlockingStub;
import com.message.proto.PollServerProcessorGrpc.PollServerProcessorStub;
import com.message.proto.Pollserverqueue.PollServerReply;
import com.message.proto.Pollserverqueue.PollServerRequest;
import com.message.proto.Query.QueryReply;
import com.message.proto.Query.QueryRequest;
import com.message.proto.QueryProcessorGrpc;
import com.message.proto.QueryProcessorGrpc.QueryProcessorBlockingStub;
import com.yrrhelp.grpc.Station.APIResponse;
import com.yrrhelp.grpc.Station.GetStationDataRequest;
import com.yrrhelp.grpc.stationGrpc.stationBlockingStub;

import core.SimAsyncProcess;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import services.PollServerService;

public class GrpcClient{

private LinkedBlockingQueue<GrpcClient> _queue;

	PollServerProcessorStub nonblockingStub;
	public static void main(String[] args) {
		
		GrpcClient c = new GrpcClient();
		c.doRun();
	}
	
	public void doRun() {
		
		NameResolver.Factory nameResolverFactory = new ServerAddressNameResolverFactory(
	            new InetSocketAddress("localhost", 9090),
	            new InetSocketAddress("localhost", 9091),
	            new InetSocketAddress("localhost", 9092),
	            new InetSocketAddress("localhost", 9093)
	    );
		
		int no_of_clients_spokes = 4;
		//creating clients
		for(int i = 1;i <= no_of_clients_spokes; i++) {
			// channel created for ports 9090,9091,9092,9093 with the server
			ManagedChannel channel = ManagedChannelBuilder.forTarget("192.168.1.84").nameResolverFactory(nameResolverFactory).maxInboundMessageSize(1024*1024*1024).usePlaintext().build();			
			nonblockingStub = PollServerProcessorGrpc.newStub(channel);
			
		}		
//		for(int i = 1;i<=4;i++) { // 4 clients
		    Channel channel  = nonblockingStub.getChannel();
		    channel.authority();
			Instant start = Instant.now();
			PollServerService pss = new PollServerService();
			
			
//			nonblockingStub.
//			System.out.println("Client id: "+i+" is polling the server");
//			PollServerRequest psr = PollServerRequest.newBuilder().setClientId(i).build();
			
			
			
			//QueryRequest request = QueryRequest.newBuilder().setFile(fileStr).build();
			//APIResponse res = blockingStub.getStationData(request);
			//System.out.println("Client "+i+" Sending File to server");
			io.grpc.stub.StreamObserver<PollServerReply> responseObserver = null;
			nonblockingStub.poll(psr, responseObserver);
			if(responseObserver!=null) {
				
			}
			Instant end = Instant.now();
			//System.out.println("End: "+end);
			System.out.println("Time took to process the request: "+Duration.between(start, end));
			//StreamObservers.copyWithFlowControl(res, new StreamObserverWithCallbacks<>());
			System.out.println("response: "+i);
//		}
	}

}

