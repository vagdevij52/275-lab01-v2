package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.protobuf.ByteString;
import com.message.proto.FileServiceGrpc;
import com.message.proto.File.UploadFileRequest;
import com.message.proto.FileServiceGrpc.FileServiceBlockingStub;
import com.message.proto.FileServiceGrpc.FileServiceStub;
import com.message.proto.Query.QueryReply;
import com.message.proto.Query.QueryRequest;
import com.message.proto.QueryProcessorGrpc;
import com.message.proto.QueryProcessorGrpc.QueryProcessorBlockingStub;
import com.yrrhelp.grpc.stationGrpc;
import com.yrrhelp.grpc.stationGrpc.stationBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.stub.StreamObserver;
import parser.MesonetProcessor;

public class Client_BlockingRequest {
	
	Map<Integer,String> map = new HashMap<>();
	MesonetProcessor mp = new MesonetProcessor();
	JSONArray result = new JSONArray();
	QueryReply reply = null;
	public Map createDict() {
		map.put(1, "201202021400");
		map.put(2, "201202021700");
		map.put(3, "201202021900");
		map.put(4, "201202022000");
		map.put(5, "201202022100");
		return map;		
	}
	
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		Client_BlockingRequest cl = new Client_BlockingRequest();
		StationService ss = new StationService();
		MesonetProcessor mp = new MesonetProcessor();
;		// channel created for port 9090 with the server
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("192.168.1.84",9090) //192.168.1.84  localhost
				.maxInboundMessageSize(1024*1024*1024)
				.usePlaintext().build();

		QueryProcessorBlockingStub blockingStub = QueryProcessorGrpc.newBlockingStub(channel);
		cl.makeRequest(blockingStub);
		
	}
	
	public void makeRequest(QueryProcessorBlockingStub blockingStub) throws ParseException, FileNotFoundException {
		Map<Integer,String> filenames = createDict();
		long startTime = System.currentTimeMillis();
		for(int i = 1;i<=filenames.size();i++) {
			File file = mp.processFile(filenames.get(i));
			String res = processRequest(file);
			QueryRequest request = QueryRequest.newBuilder().setQuery(res).build();
			reply = blockingStub.sendQuery(request);
		}
		long stopTime = System.currentTimeMillis();
		System.out.println(
				"total request processing for blocking client time is " + ((stopTime - startTime) / 1000.0) + " seconds");
	}
	
	public String processRequest(File f) throws FileNotFoundException {
		Scanner sc = new Scanner(f);
		if(f.exists()) {
			while (sc.hasNext()) { //returns a boolean value
				JSONObject res = new JSONObject();
				String values[] = sc.nextLine().split(",");
				  res.put("id", values[0]);
				  res.put("name", values[1]);
				  res.put("mesonet", values[2]);
				  res.put("lat", values[3]);
				  res.put("lon", values[4]);
				  res.put("elevation", values[5]);
				  res.put("agl", values[6]);
				  res.put("cit", values[7]);
				  res.put("state", values[8]);
				  res.put("country", values[9]);
				  res.put("active", values[10]);
				result.add(res);					
		}   
		sc.close();
		}
		String res = result.toJSONString();
		return res;
	}


}
