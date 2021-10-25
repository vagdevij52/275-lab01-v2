package client;


import com.message.proto.File.UploadFileResponse;

import io.grpc.stub.StreamObserver;

class FileUploadObserver implements StreamObserver<UploadFileResponse> {

    @Override
    public void onNext(UploadFileResponse fileUploadResponse) {
//        System.out.println(
//                "File upload status :: " + fileUploadResponse.getStatus()
//        );
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }

}