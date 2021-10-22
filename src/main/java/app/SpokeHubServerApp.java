package app;

import java.util.ArrayList;
import java.util.List;

import basic.SpokeHubBuilder;
import core.SimException;
import core.SimProcess;
import core.SimWork;
import io.grpc.Server;
import server.GRPCServer;

public class SpokeHubServerApp {

	public void buildhubspoke() {
		Server hub = SpokeHubBuilder.create(5);
		//GRPCServer hubServer = (GRPCServer) SpokeHubBuilder.create(5);
		//hub.startup();

		for (int n = 0; n < 30; n++) {
			SimWork w = new SimWork(n);
			w.setData(n); // placeholder

			try {
				//hub.run(w);
			} catch (Exception e) {
				// ack!
				e.printStackTrace();
			}
		}
		
		System.out.println("work added to hub");

		//spin-lock - polling within a single thread
		// don't allow main to exit while we still have work
		
//		while (hub.hasWork()) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//				break;
//			}
//		}
		
		hub.shutdown();
		System.out.println("bye!");
		
		// TODO how would you print stats?
	}

}
