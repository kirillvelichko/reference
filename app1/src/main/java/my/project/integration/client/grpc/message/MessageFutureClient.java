package my.project.integration.client.grpc.message;

import lombok.RequiredArgsConstructor;
import my.project.gen.grpc.TestResponse;
import my.project.gen.grpc.TestServiceGrpc;
import my.project.integration.client.grpc.FutureExecutor;
import my.project.integration.client.grpc.message.mapper.MessageMapper;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class MessageFutureClient {
    private final TestServiceGrpc.TestServiceFutureStub testService;
    private final MessageMapper messageMapper;
    private final FutureExecutor futureExecutor;

    public CompletableFuture<TestResponse> test(String messageText) {
        var request = messageMapper.toRequest(messageText);
        var future = testService.test(request);
        return futureExecutor.exec(future);
    }
}
