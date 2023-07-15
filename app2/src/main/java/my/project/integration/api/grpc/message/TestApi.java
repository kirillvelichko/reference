package my.project.integration.api.grpc.message;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import my.project.gen.grpc.MessageRequest;
import my.project.gen.grpc.MessageResponse;
import my.project.gen.grpc.TestServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@Slf4j
public class TestApi extends TestServiceGrpc.TestServiceImplBase {

    @Override
    public void getMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        log.info("Received message: {}", request.getText());
        var response = MessageResponse.newBuilder()
                .setMessage("App2 received: " + request.getText())
                .build();
        if (request.hasText2()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("message2 is not allowed")
                    .asRuntimeException());
            return;
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
