package basic;

import java.util.Iterator;

import client.QueryClientUtil;
import core.SimAsyncProcess;
import core.SimException;
import core.SimProcess;
import core.SimWork;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.GRPCServer;
import server.StationService;

public class SpokeHubBuilder {

	/**
	 * create a new spoke and hub topology where the hub is asynchronous and the
	 * spokes are not.
	 * 
	 * You must call startup() on the hub to initialize hub.
	 * 
	 * @param numSpokes
	 * @return
	 */
//	public static final SimProcess create(int numSpokes) {
//		SimProcess hub = new HubProcess(0);
//
//		if (numSpokes > 0) {
//			for (int n = 0; n < numSpokes; n++) {
//				SimProcess spoke = new SimProcess(n + 1);
//				spoke.startup();
//				hub.addConnection(spoke); 
//			}
//		}
//
//		return hub;
//	}
	
	public static final Server create(int numSpokes) {
		//SimProcess hub = new HubProcess(0);
		//Server hubserver = ServerBuilder.forPort(9090).addService(new QueryClientUtil()).build();
		
		
		
//		if (numSpokes > 0) {
//			for (int n = 1; n <=numSpokes; n++) {
//				//SimProcess spoke = new SimProcess(n + 1);
//				//spoke.startup();
//				//hub.addConnection(spoke); 
//			}
//		}

		return null;
	}

	public static class HubProcess extends SimAsyncProcess {

		public HubProcess(int id) {
			super(id);
		}

		@Override
		public void shutdown() {
			super.shutdown();
			Iterator<SimProcess> iter = this.getConnections();
			while (iter.hasNext()) {
				iter.next().shutdown();
			}
		}

		//async process which enables asynchronous behaviour through hub and spoke design pattern
		@Override
		public void doRun() {
			Iterator<SimWork> q = this.getQueueIterator();

			if (!q.hasNext())
				return;

			// round-robin on connections. if there is more work than
			// connections, the AsyncRun can call doRun() again.
			Iterator<SimProcess> c = this.getConnections();
			while (c.hasNext()) {
				if (!q.hasNext())
					break;
				SimWork w = q.next();
				SimProcess p = c.next();
				try {
					// debugging
					System.out.println("--> work " + w.getId() + " processed by " + p.getId());
					System.out.flush();

					p.run(w);
					q.remove();
				} catch (SimException e) {
					// ack! watch out for poison pills!
					e.printStackTrace();
				}
			}
		}

	}
}
