package my.project.integration.grpc;

import io.grpc.stub.StreamObserver;
import my.project.integration.grpc.gen.TestRequest;
import my.project.integration.grpc.gen.TestResponse;
import my.project.integration.grpc.gen.TestServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class TestApi extends TestServiceGrpc.TestServiceImplBase {

    @Override
    public void test(TestRequest request, StreamObserver<TestResponse> responseObserver) {
        System.out.println("App2: grpc test");
        System.out.println(request.getFirstName());
        System.out.println(request.getLastName());
        TestResponse response = TestResponse.newBuilder()
                .setGreeting("Hi " + request.getFirstName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
