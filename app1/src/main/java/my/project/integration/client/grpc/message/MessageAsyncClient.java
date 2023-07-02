package my.project.integration.client.grpc.message;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import my.project.gen.grpc.TestResponse;
import my.project.gen.grpc.TestServiceGrpc;
import my.project.integration.client.grpc.message.mapper.MessageMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageAsyncClient {
    private final TestServiceGrpc.TestServiceStub testService;
    private final MessageMapper messageMapper;

    public void test(String messageText, StreamObserver<TestResponse> callback) {
        var request = messageMapper.toRequest(messageText);
        testService.test(request, callback);
    }
}
