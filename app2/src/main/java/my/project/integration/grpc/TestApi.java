package my.project.integration.grpc;

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
        log.info("Test request has received");
        TestResponse response = TestResponse.newBuilder()
                .setGreeting("Hi " + request.getFirstName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
