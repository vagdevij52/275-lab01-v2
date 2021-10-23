package client;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import com.yrrhelp.grpc.stationGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import com.message.proto.FileProcessorGrpc;
import com.message.proto.FileProcessorGrpc.FileProcessorFutureStub;
import com.message.proto.FileReply;
import com.message.proto.FileRequest;
import com.message.proto.FileUploadObserver;
import com.message.proto.PollServerProcessorGrpc;
import com.message.proto.PollServerProcessorGrpc.PollServerProcessorBlockingStub;
import com.message.proto.PollServerProcessorGrpc.PollServerProcessorFutureStub;
import com.message.proto.PollServerProcessorGrpc.PollServerProcessorStub;
import com.message.proto.Pollserverqueue.PollServerReply;
import com.message.proto.Pollserverqueue.PollServerRequest;
import com.message.proto.Query.QueryReply;
import com.message.proto.Query.QueryRequest;
import com.message.proto.QueryProcessorGrpc;
import com.message.proto.QueryProcessorGrpc.QueryProcessorBlockingStub;
import com.message.proto.QueryProcessorGrpc.QueryProcessorFutureStub;
import com.yrrhelp.grpc.Station.APIResponse;
import com.yrrhelp.grpc.Station.GetStationDataRequest;
import com.yrrhelp.grpc.stationGrpc.stationBlockingStub;

import core.SimAsyncProcess;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.stub.StreamObserver;
import parser.MesonetProcessor;
import services.PollServerService;


public class GrpcClient{

	private LinkedBlockingQueue<GrpcClient> _queue;
	Map<Integer,String> map = new HashMap<>();
	//PollServerProcessorStub nonblockingStub;
	//private final int _id;
	private final Integer _mutex;
	private final Random _rand;
	private final int _maxSleep;

	private int _numRuns;
	private long _lastRan;
	private long _accumTime;
	
	public GrpcClient() {
		// TODO Auto-generated constructor stub
		_mutex = Integer.valueOf(1);
		_rand = new Random(System.nanoTime());
		_maxSleep = 10; // msec
		reset();

	}
	
	public void reset() {
		_numRuns = 0;
		_lastRan = 0;
		_accumTime = 0;
	}
	
	public static void main(String[] args) {
		
		GrpcClient c = new GrpcClient();
		c.doRun();
	}
	
	public Map createDict() {
		
		map.put(1, "201202012000");
		map.put(2, "201202012100");
		map.put(3, "201202012200");
		map.put(4, "201202012300");
		map.put(5, "201202021200");
		map.put(6, "201202021400");
		map.put(7, "201202021700");
		map.put(8, "201202021900");
		map.put(9, "201202022000");
		map.put(10, "201202022100");
		return map;		
	}
	
	public void doRun() {
		StreamObserver<FileRequest> streamObserver = this.fileServiceStub.upload(new FileUploadObserver());
		
		
		NameResolver.Factory nameResolverFactory = new ServerAddressNameResolverFactory(
	            new InetSocketAddress("192.168.1.84", 9090),
	            new InetSocketAddress("192.168.1.84", 9091),
	            new InetSocketAddress("192.168.1.84", 9092),
	            new InetSocketAddress("192.168.1.84", 9093)
	    );
		
		ManagedChannel channel = ManagedChannelBuilder.forTarget("service")
				.nameResolverFactory(nameResolverFactory)
				.defaultLoadBalancingPolicy("round_robin")
				.maxInboundMessageSize(1024*1024*1024)
				.usePlaintext().build();
		int no_of_clients_spokes = 4;
		long startTime = System.currentTimeMillis();
		//creating clients
		for(int i = 1;i <= map.size(); i++) {
			// channel created for ports 9090,9091,9092,9093 with the server			
			FileProcessorFutureStub futureStub = FileProcessorGrpc.newFutureStub(channel);
			File csvFile = run(map.get(i));
			
			InputStream inputStream = Files.newInputStream(csvFile);
			byte[] bytes = new byte[4096];
			int size;
			while ((size = inputStream.read(bytes)) > 0){
			    FileRequest uploadRequest = FileRequest.newBuilder()
			            .setFile(File.newBuilder().setContent(ByteString.copyFrom(bytes, 0 , size)).build())
			            .build();
			    streamObserver.onNext(uploadRequest);
			}
			
			
			FileRequest request = FileRequest.newBuilder().setFile(b);
					
			//PollServerRequest req =  PollServerRequest.newBuilder().setClientId(i).build();
			//ListenableFuture<PollServerReply> resp = nonblockingStub.poll(req);
			ListenableFuture<FileReply> resp = futureStub.upload(csvFile);
			resp.addListener(() -> {
				try {
					long stopTime = System.currentTimeMillis();
					System.out.println("Server gave:==>"+ resp.get().getFilename());
					run(resp.get().getFilename());
					System.out.println(
							"total request processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}, command -> command.run());
			
			System.out.println("client "+i+"connected to server");
			
		}	
		
		
		
//		    Channel channel  = nonblockingStub.getChannel();
//		    channel.authority();
//			Instant start = Instant.now();
//			PollServerService pss = new PollServerService();
//			
//			
//			nonblockingStub.
//			System.out.println("Client id: "+i+" is polling the server");
//			PollServerRequest psr = PollServerRequest.newBuilder().setClientId(i).build();
//			
//			
//			
//			//QueryRequest request = QueryRequest.newBuilder().setFile(fileStr).build();
//			//APIResponse res = blockingStub.getStationData(request);
//			//System.out.println("Client "+i+" Sending File to server");
//			io.grpc.stub.StreamObserver<PollServerReply> responseObserver = null;
//			nonblockingStub.poll(psr, responseObserver);
//			if(responseObserver!=null) {
//				
//			}
//			Instant end = Instant.now();
//			//System.out.println("End: "+end);
//			System.out.println("Time took to process the request: "+Duration.between(start, end));
//			//StreamObservers.copyWithFlowControl(res, new StreamObserverWithCallbacks<>());
//			System.out.println("response: "+i);
	}
	
	
	public File run(String filename) throws Exception {
		File csvFile = null;
		synchronized (_mutex) {
			try {
				long startTime = System.currentTimeMillis();
				MesonetProcessor mp = new MesonetProcessor();
				csvFile = mp.processFile(filename);
				// --------------------------------------------
				// TODO do something here...evaluate success
				// --------------------------------------------
				Thread.sleep(_rand.nextInt(_maxSleep) + 1);
				long stopTime = System.currentTimeMillis();
				System.out.println(
						"total file processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
			} catch (Exception e) {
				// Status is better than: _good = false;
				System.out.println(e.getMessage());
			}
			return csvFile;
		}
	}
	
}

