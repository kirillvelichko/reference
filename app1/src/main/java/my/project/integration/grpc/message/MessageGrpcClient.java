package my.project.integration.grpc.message;

import lombok.RequiredArgsConstructor;
import my.project.gen.grpc.TestRequest;
import my.project.gen.grpc.TestResponse;
import my.project.gen.grpc.TestServiceGrpc.TestServiceBlockingStub;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageGrpcClient {
    private final TestServiceBlockingStub testService;

    public TestResponse test(TestRequest request) {
        return testService.test(request);
    }
}
