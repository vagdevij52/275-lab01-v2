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



import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.stub.StreamObserver;
import parser.MesonetProcessor;

public class Client_2_FileUploadStream{

	
	Map<Integer,String> map = new HashMap<>();
	MesonetProcessor mp = new MesonetProcessor();
	public Map createDict() {
		map.put(1, "201202021400");
		map.put(2, "201202021700");
		map.put(3, "201202021900");
		map.put(4, "201202022000");
		map.put(5, "201202022100");
		return map;		
	}
	
	public static void main(String[] args) throws ParseException {
		Client_2_FileUploadStream cl = new Client_2_FileUploadStream();
		MesonetProcessor mp = new MesonetProcessor();
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
		

		FileServiceStub st = FileServiceGrpc.newStub(channel);
		Map<Integer,String> filenames = cl.createDict();
		
		long startTime = System.currentTimeMillis();
		for(int i = 1;i<=filenames.size();i++) {
			File file = mp.processFile(filenames.get(i));
			byte[] bytes = new byte[(int) file.length()];
			StreamObserver<UploadFileRequest> streamObserver = st.uploadfile(new FileUploadObserver());
			UploadFileRequest uploadRequest = UploadFileRequest.newBuilder()
			            .setFile(ByteString.copyFrom(bytes)).build();
			    streamObserver.onNext(uploadRequest);
		}
		long stopTime = System.currentTimeMillis();
		System.out.println(
				"total request processing for client 2 time is " + ((stopTime - startTime) / 1000.0) + " seconds");
	}

}
