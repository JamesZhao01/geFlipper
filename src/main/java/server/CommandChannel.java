package server;

import com.google.protobuf.Any;
import io.grpc.stub.StreamObserver;

public class CommandChannel implements StreamObserver<Any> {
    public StreamObserver<Any> responseObserver;
    public CommandChannel(StreamObserver<Any> responseObserver) {
        this.responseObserver = responseObserver;
    }
    @Override
    public void onNext(Any any) {
        responseObserver.onNext(any);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("DUDE");
    }

    @Override
    public void onCompleted() {
        responseObserver.onCompleted();
    }
}