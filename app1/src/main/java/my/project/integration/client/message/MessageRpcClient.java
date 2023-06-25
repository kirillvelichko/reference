package my.project.integration.client.message;

import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import my.project.gen.grpc.TestRequest;
import my.project.gen.grpc.TestResponse;
import my.project.gen.grpc.TestServiceGrpc.TestServiceBlockingStub;
import my.project.integration.client.message.exception.SendingMessageException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRpcClient {
    private final TestServiceBlockingStub testService;

    public TestResponse test(TestRequest request) throws SendingMessageException {
        try {
            return testService.test(request);
        } catch (StatusRuntimeException e) {
            throw new SendingMessageException(e);
        }
    }
}
