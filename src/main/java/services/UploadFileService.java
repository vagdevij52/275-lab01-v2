package services;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

import com.google.protobuf.ByteString;
import com.message.proto.File.Status;
import com.message.proto.File.UploadFileRequest;
import com.message.proto.File.UploadFileResponse;
import com.message.proto.FileServiceGrpc.FileServiceImplBase;

import io.grpc.stub.StreamObserver;

public class UploadFileService extends FileServiceImplBase {


	private static final Path SERVER_BASE_PATH = Paths.get("src/test/resources/output");
	private final LinkedList<ByteString> chunkQueue = new LinkedList<ByteString>();
	
	
//	@Override
//	public StreamObserver<UploadFileRequest> uploadfile(StreamObserver<UploadFileResponse> responseObserver) {
//		// TODO Auto-generated method stub
//		return super.uploadfile(responseObserver);
//	}
	
   
	 @Override
	    public StreamObserver<UploadFileRequest> uploadfile(StreamObserver<com.message.proto.File.UploadFileResponse> responseObserver) {
	        return new StreamObserver<UploadFileRequest>() {
	            // upload context variables
	            OutputStream writer;
	            Status status = Status.IN_PROGRESS;

	            @Override
	            public void onNext(UploadFileRequest fileUploadRequest) {
	                try{
	                	System.out.println("fileUploadRequest.getFile() : "+fileUploadRequest.getFile());
	                	if(fileUploadRequest.toByteArray().length>0) {
	                	chunkQueue.push(fileUploadRequest.getFile());
	                	}
	                	//File f = new File(fileUploadRequest.getFile());
	                    //writeFile(writer, fileUploadRequest.getFile());
	                }catch (Exception e){
	                    this.onError(e);
	                }
	            }

	            @Override
	            public void onError(Throwable throwable) {
	                status = Status.FAILED;
	                this.onCompleted();
	            }

	            @Override
	            public void onCompleted() {
	                //closeFile(writer);
	                status = Status.IN_PROGRESS.equals(status) ? Status.SUCCESS : status;
	                UploadFileResponse response = UploadFileResponse.newBuilder()
	                        .setStatus(status)
	                        .build();
	                responseObserver.onNext(response);
	                responseObserver.onCompleted();
	            }
	        };
	    }

//    private OutputStream getFilePath(UploadFileRequest request) throws IOException {
//        var fileName = request.getMetadata().getName() + "." + request.getMetadata().getType();
//        return Files.newOutputStream(SERVER_BASE_PATH.resolve(fileName), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//    }

//    private void writeFile(OutputStream writer, ByteString content) throws IOException {
//        writer.write(content.toByteArray());
//        writer.flush();
//    }

//    private void closeFile(OutputStream writer){
//        try {
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}