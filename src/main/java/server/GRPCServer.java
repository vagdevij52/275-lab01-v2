package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.QueryServerService;
public class GRPCServer{

	private LinkedBlockingQueue<String> _queue;
	public GRPCServer() {
		//putting filenames into the queue for the clients to poll the queue and get the filenames to process them further
		_queue = new LinkedBlockingQueue<String>();
	}

//	 @Override
//		public void shutdown() {
//			super.shutdown();
//			Iterator<SimProcess> iter = this.getConnections();
//			while (iter.hasNext()) {
//				iter.next().shutdown();
//			}
//		}
	 
//	public void doRun() throws IOException, InterruptedException {			
//			String port = "9090";
//			GRPCServer grpcServer = new GRPCServer();
//			Server server = ServerBuilder.forPort(9090).addService(
//					 new QueryServerService()).build();
//			Map<Integer,String> map = new HashMap<>();
//			map.put(1, "201202012000");
//			map.put(2, "201202012100");
//			map.put(3, "201202012200");
//			map.put(4, "201202012300");
//			map.put(5, "201202021200");
//			map.put(6, "201202021400");
//			map.put(7, "201202021700");
//			map.put(8, "201202021900");
//			map.put(9, "201202022000");
//			map.put(10, "201202022100");
//			//async behaviour of server/ async behaviour of hub
//			// each client which tries to connect with this server at 9090 port is one thread
//			while (true) {
//			    //accept a connection;
//			    //create a thread to deal with the client;
//				Thread thread = new Thread() {
//					public void run() {
//						try {
//							int i = 1;
//							server.start();
//							//clients poll this queue
//							_queue.add(map.get(i));
//							System.out.println("Server "+port+" listening on port "+server.getPort());
//							server.awaitTermination();
//						} catch(Exception ie) {
//							ie.printStackTrace();
//						}
//					}
//				};
//				thread.start();
//			}
//	 }
	
	
	public static void main(String args[]) throws IOException, InterruptedException{
		for(int i=0;i<=3;i++) {
			 int p = 9090+i;
			 System.out.println("starting GRPC Server");
			 GRPCServer grpcServer = new GRPCServer();
			 Server server = ServerBuilder.forPort(p).addService(
					 new QueryServerService(grpcServer,i)).build();
			 Thread thread = new Thread() {
					public void run() {
						try {
							server.start();
							System.out.println("Server "+p+" listening on port "+server.getPort());
							server.awaitTermination();
						} catch(Exception ie) {
							ie.printStackTrace();
						}
					}
				};
				thread.start();
			 
			 
			 //server.start();
			 //System.out.println("server started at "+ server.getPort());
		        //server.awaitTermination(); 
		 }
		//s.doRun();
	}

//	@Override
//	public void doRun() {
//		// TODO Auto-generated method stub
//		
//	}
}
