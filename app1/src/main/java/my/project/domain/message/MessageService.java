package my.project.domain.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.integration.client.grpc.message.MessageAsyncClient;
import my.project.integration.client.grpc.message.MessageBlockingClient;
import my.project.integration.client.grpc.message.callback.MessageCallback;
import my.project.integration.client.grpc.message.exception.SendingMessageException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageBlockingClient messageClient;
    private final MessageAsyncClient messageAsyncClient;
    private final MessageCallback messageCallback;

    public String sendMessage(String messageText) {
        String responseMessage;
        try {
            responseMessage = messageClient.test(messageText);
        } catch (SendingMessageException e) {
            log.error("Sending message error", e);
            return "Failed to send message";
        }
        return responseMessage;
    }

    public void sendMessageAsync(String messageText) {
        log.info("Sending message: {}", messageText);
        messageAsyncClient.test(messageText, messageCallback);
    }
}
