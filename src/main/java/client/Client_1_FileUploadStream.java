package client;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.ByteString;
import com.message.proto.File.UploadFileRequest;
import com.message.proto.File.UploadFileResponse;
import com.message.proto.FileServiceGrpc;
import com.message.proto.FileServiceGrpc.FileServiceStub;
import com.yrrhelp.grpc.stationGrpc;
import com.yrrhelp.grpc.Station.APIResponse;
import com.yrrhelp.grpc.Station.GetStationDataRequest;
import com.yrrhelp.grpc.stationGrpc.stationBlockingStub;



import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.stub.StreamObserver;
import parser.MesonetProcessor;

public class Client_1_FileUploadStream{

	
	Map<Integer,String> map = new HashMap<>();
	MesonetProcessor mp = new MesonetProcessor();
	public Map createDict() {
		
//		map.put(1, "201202012000");
//		map.put(2, "201202012100");
//		map.put(3, "201202012200");
//		map.put(4, "201202012300");
//		map.put(5, "201202021200");
		
		map.put(1, "201402010500");
		map.put(2, "201402010400");
		map.put(3, "201402010300");
		map.put(4, "201402010200");
		map.put(5, "201402010100");
		return map;		
	}
	
	public static void main(String[] args) throws ParseException {
		Client_1_FileUploadStream cl = new Client_1_FileUploadStream();
		StationService ss = new StationService();
		MesonetProcessor mp = new MesonetProcessor();
;		// channel created for port 9090 with the server
		NameResolver.Factory nameResolverFactory = new MultiAddressNameResolverFactory(
        new InetSocketAddress("localhost", 9090),
        new InetSocketAddress("localhost", 9091),
        new InetSocketAddress("localhost", 9092)
		);
		
		ManagedChannel channel = ManagedChannelBuilder
				.forTarget("service") //192.168.1.84  localhost
				.nameResolverFactory(nameResolverFactory)
				.defaultLoadBalancingPolicy("round_robin")
				.maxInboundMessageSize(1024*1024*1024)
				.usePlaintext().build();
		
		// we need stubs to call a particular API
		//fileStub stub = fileGrpc.newStub(channel);
		FileServiceStub st = FileServiceGrpc.newStub(channel);
		//FileProcessorGrpc.FileProcessorStub blockingStub = FileProcessorGrpc.newStub(channel);
		Map<Integer,String> filenames = cl.createDict();
		
		long startTime = System.currentTimeMillis();
		for(int i = 1;i<=filenames.size();i++) {
			File file = mp.processFile(filenames.get(i));
			byte[] bytes = new byte[(int) file.length()];
			StreamObserver<UploadFileRequest> streamObserver = st.uploadfile(new FileUploadObserver());
			//StreamObserver<UploadFileRequest>  request = UploadFileRequest.newBuilder().setFile(null).build();
			
			//String message = st.uploadfile(UploadFileRequest.newBuilder().setFile(ByteString.copyFrom(bytes)).build()).getStatus();
			
			
			UploadFileRequest uploadRequest = UploadFileRequest.newBuilder()
			            .setFile(ByteString.copyFrom(bytes)).build();
			    streamObserver.onNext(uploadRequest);
		}
		long stopTime = System.currentTimeMillis();
		System.out.println(
				"total request processing for client 1 time is " + ((stopTime - startTime) / 1000.0) + " seconds");
	}

}
