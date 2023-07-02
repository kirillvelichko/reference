package my.project.integration.api.grpc.message;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import my.project.gen.grpc.TestRequest;
import my.project.gen.grpc.TestResponse;
import my.project.gen.grpc.TestServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@Slf4j
public class TestApi extends TestServiceGrpc.TestServiceImplBase {

    @Override
    public void test(TestRequest request, StreamObserver<TestResponse> responseObserver) {
        log.info("Received message: {}", request.getMessage());
        TestResponse response = TestResponse.newBuilder()
                .setMessage("App2 received: " + request.getMessage())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
