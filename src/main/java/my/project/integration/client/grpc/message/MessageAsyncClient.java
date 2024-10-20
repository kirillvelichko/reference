package my.project.integration.client.grpc.message;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import my.project.gen.grpc.MessageResponse;
import my.project.gen.grpc.TestServiceGrpc;
import my.project.integration.client.grpc.message.mapper.MessageMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageAsyncClient {
    private final TestServiceGrpc.TestServiceStub testService;
    private final MessageMapper messageMapper;

    public void getMessage(String messageText, StreamObserver<MessageResponse> callback) {
        var request = messageMapper.toRequest(messageText);
        testService.getMessage(request, callback);
    }
}
