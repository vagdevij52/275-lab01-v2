package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import services.UploadFileBlockingService;
import services.UploadFileService;

public class GRPCServer{
	
	private static Server server;

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
		
//		 System.out.println("starting GRPC Server");
//		 Server server = ServerBuilder.forPort(9090).addService(
//
//				 new UploadFileService()).build();
//		 
//		 server.start();
//		 System.out.println("server started at "+ server.getPort());
//	        server.awaitTermination();
	        
	        
	        try {
				var serverBuilder = configureExecutor(ServerBuilder.forPort(9090));
				 server = serverBuilder
						 .addService(new UploadFileService())
						 .addService(new UploadFileBlockingService())
						 .maxInboundMessageSize(1024*1024*1024).build();
				 server.start();
				 
				 System.out.println("server started at "+ server.getPort());
			        server.awaitTermination();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	private static ServerBuilder<?> configureExecutor(ServerBuilder<?> sb) {
        var threads = System.getenv("JVM_EXECUTOR_THREADS");
        var i_threads = Runtime.getRuntime().availableProcessors();
        if (threads != null && !threads.isEmpty()) {
          i_threads = Integer.parseInt(threads);
        }


        var value = System.getenv().getOrDefault("JVM_EXECUTOR_TYPE", "single");
        switch (value) {
          case "direct": sb = sb.directExecutor();
          case "single": sb = sb.executor(Executors.newSingleThreadExecutor());
          case "fixed": sb = sb.executor(Executors.newFixedThreadPool(i_threads));
          case "workStealing": sb = sb.executor(Executors.newWorkStealingPool(2*i_threads));
          case "cached": sb = sb.executor(Executors.newCachedThreadPool());
        }

        return sb;
      }
}
