package server;

import java.io.IOException;
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
	        try {	        	
	        	for(int i=0;i<=2;i++) {
	    			final int p = i;
	    			int port = 9090+p;
	    			var serverBuilder = ServerBuilder.forPort(port).executor(Executors.newSingleThreadExecutor());
					 server = serverBuilder
							 .addService(new UploadFileService())
							 .addService(new UploadFileBlockingService())
							 .maxInboundMessageSize(1024*1024*1024).build();
					 server.start();
					 
					 System.out.println("server started at "+ server.getPort());
				        server.awaitTermination();
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
