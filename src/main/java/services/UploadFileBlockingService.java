package services;

import com.message.proto.Query.QueryReply;
import com.message.proto.Query.QueryRequest;
import com.message.proto.QueryProcessorGrpc.QueryProcessorImplBase;

import io.grpc.stub.StreamObserver;

public class UploadFileBlockingService extends QueryProcessorImplBase{

	@Override
	public void sendQuery(QueryRequest request, StreamObserver<QueryReply> responseObserver) {
		// TODO Auto-generated method stub
		System.out.println("Inside sendQuery");
		
		String query = request.getQuery();
		
		QueryReply.Builder res = QueryReply.newBuilder();
		if(!query.isEmpty()) {
			res.setMessage(query);
		}
		responseObserver.onNext(res.build());
		responseObserver.onCompleted();
	}

	

}
