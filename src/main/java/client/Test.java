package client;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.message.proto.PollServerProcessorGrpc;
import com.message.proto.PollServerProcessorGrpc.PollServerProcessorStub;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Map<Integer,String> map = new HashMap<>();
//		map.put(1, "201202012000");
//		map.put(2, "201202012100");
//		map.put(3, "201202012200");
//		map.put(4, "201202012300");
//		map.put(5, "201202021200");
//		System.out.println(map.size());
//		for(int i=1;i<=map.size();i++) {
//			System.out.println(map.get(i));
//		}
//		
		PollServerProcessorStub nonblockingStub = null;
		
		
		NameResolver.Factory nameResolverFactory = new ServerAddressNameResolverFactory(
	            new InetSocketAddress("localhost", 9090),
	            new InetSocketAddress("localhost", 9091),
	            new InetSocketAddress("localhost", 9092),
	            new InetSocketAddress("localhost", 9093)
	    );
		
		int no_of_clients_spokes = 4;
		for(int i = 1;i <= no_of_clients_spokes; i++) {
			// channel created for ports 9090,9091,9092,9093 with the server
			ManagedChannel channel = ManagedChannelBuilder.forTarget("192.168.1.84").nameResolverFactory(nameResolverFactory).maxInboundMessageSize(1024*1024*1024).usePlaintext().build();			
			nonblockingStub = PollServerProcessorGrpc.newStub(channel);
			
		}
		Channel channel  = nonblockingStub.getChannel();
	    System.out.println("channel auth: "+channel.authority());
	}
}

