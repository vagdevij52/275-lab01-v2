package client;

import java.io.File;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.ByteString;
import com.message.proto.FileServiceGrpc;
import com.message.proto.File.UploadFileRequest;
import com.message.proto.FileServiceGrpc.FileServiceStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import parser.MesonetProcessor;

public class Client_3_FileUploadStream {

	Map<Integer,String> map = new HashMap<>();
	MesonetProcessor mp = new MesonetProcessor();
	public Map createDict() {
		map.put(1, "201202012000");
		map.put(2, "201202012100");
		map.put(3, "201202012200");
		map.put(4, "201202012300");
		map.put(5, "201202021200");
		return map;		
	}
	
	public static void main(String[] args) throws ParseException {
		Client_3_FileUploadStream cl = new Client_3_FileUploadStream();
		StationService ss = new StationService();
		MesonetProcessor mp = new MesonetProcessor();
;		// channel created for port 9090 with the server
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost",9090) //192.168.1.84  localhost
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
				"total request processing for client 2 time is " + ((stopTime - startTime) / 1000.0) + " seconds");
	}
	
}
