package client;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import com.google.protobuf.ByteString;
import com.message.proto.FileServiceGrpc.FileServiceImplBase;
import com.message.proto.FileServiceGrpc.FileServiceStub;
import com.message.proto.FileUploadRequest;
import com.message.proto.FileUploadResponse;
import com.message.proto.MetaData;

import io.grpc.stub.StreamObserver;

public class FileUploadService extends FileServiceImplBase{

	FileServiceStub fileServiceStub;
	
	@Override
    public StreamObserver<FileUploadRequest> upload(StreamObserver<FileUploadResponse> responseObserver) {
		// request observer
		StreamObserver<FileUploadRequest> streamObserver = this.fileServiceStub.upload(new FileUploadObserver());

		// input file for testing
		Path path = Paths.get("src/test/resources/input/java_input.pdf");

		// build metadata
		FileUploadRequest metadata = FileUploadRequest.newBuilder()
		        .setMetadata(MetaData.newBuilder()
		                .setName("output")
		                .setType("pdf").build())
		        .build();
		streamObserver.onNext(metadata);

		// upload file as chunk
		InputStream inputStream = Files.newInputStream(path);
		byte[] bytes = new byte[4096];
		int size;
		
		while ((size = inputStream.read(bytes)) > 0){
		    FileUploadRequest uploadRequest = FileUploadRequest.newBuilder()
		            .setFile(File.newBuilder().setContent(ByteString.copyFrom(bytes, 0 , size)).build())
		            .build();
		    streamObserver.onNext(uploadRequest);
		}

		// close the stream
		inputStream.close();
		streamObserver.onCompleted();	
	}

}
