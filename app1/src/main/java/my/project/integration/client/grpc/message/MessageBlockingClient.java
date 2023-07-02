package my.project.integration.client.grpc.message;

import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import my.project.gen.grpc.TestResponse;
import my.project.gen.grpc.TestServiceGrpc.TestServiceBlockingStub;
import my.project.integration.client.grpc.message.exception.SendingMessageException;
import my.project.integration.client.grpc.message.mapper.MessageMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageBlockingClient {
    private final TestServiceBlockingStub testService;
    private final MessageMapper messageMapper;

    public String test(String messageText) throws SendingMessageException {
        var request = messageMapper.toRequest(messageText);
        TestResponse response;
        try {
            response = testService.test(request);
        } catch (StatusRuntimeException e) {
            throw new SendingMessageException(e);
        }
        return response.getMessage();
    }
}
