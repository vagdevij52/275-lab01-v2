package server;

import com.message.proto.QueryProcessorGrpc.QueryProcessorImplBase;

public class QueryServerService extends QueryProcessorImplBase{

	GRPCServer server;
	int servernum;
	
	public QueryServerService() {
		// TODO Auto-generated constructor stub
	}
	public QueryServerService(GRPCServer server, int servernum) {
		// TODO Auto-generated constructor stub
		this.server = server;
		this.servernum = servernum;
	}

}
