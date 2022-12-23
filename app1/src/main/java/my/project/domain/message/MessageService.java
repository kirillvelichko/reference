package my.project.domain.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.gen.grpc.TestResponse;
import my.project.integration.grpc.message.MessageRpcClient;
import my.project.integration.grpc.message.exception.SendingMessageException;
import my.project.integration.grpc.message.mapper.MessageMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRpcClient messageClient;
    private final MessageMapper messageMapper;

    public String sendMessage(String messageText) {
        var request = messageMapper.toRequest(messageText);
        TestResponse response;
        try {
            response = messageClient.test(request);
        } catch (SendingMessageException e) {
            log.error("Sending message error", e);
            return "Failed to send message";
        }
        return response.getMessage();
    }
}
