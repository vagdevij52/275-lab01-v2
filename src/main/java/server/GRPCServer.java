package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import services.UploadFileService;

public class GRPCServer{

	private LinkedBlockingQueue<String> _queue;
	public GRPCServer() {
		//putting filenames into the queue for the clients to poll the queue and get the filenames to process them further
		_queue = new LinkedBlockingQueue<String>();
	}
	public static void main(String args[]) throws IOException, InterruptedException{
		 GRPCServer grpcServer = new GRPCServer();
//		for(int i=0;i<=3;i++) {
//			 int p = 9090+i;
//			 System.out.println("starting GRPC Server");
//			 Server server = ServerBuilder.forPort(p).addService(
//					 new UploadFileService(grpcServer,i)).build();
//			 Thread thread = new Thread() {
//					public void run() {
//						try {
//							server.start();
//							System.out.println("Server "+p+" listening on port "+server.getPort());
//							server.awaitTermination();
//						} catch(Exception ie) {
//							ie.printStackTrace();
//						}
//					}
//				};
//				thread.start();
//		 }
		
		
		 System.out.println("starting GRPC Server");
		 Server server = ServerBuilder.forPort(9090).addService(

				 new UploadFileService()).build();
		 
		 server.start();
		 System.out.println("server started at "+ server.getPort());
	        server.awaitTermination();
	}
}
