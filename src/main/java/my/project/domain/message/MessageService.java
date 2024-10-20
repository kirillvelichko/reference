package my.project.domain.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.integration.client.grpc.message.MessageAsyncClient;
import my.project.integration.client.grpc.message.MessageBlockingClient;
import my.project.integration.client.grpc.message.MessageFutureClient;
import my.project.integration.client.grpc.message.callback.MessageCallback;
import my.project.integration.client.grpc.message.exception.GrpcIntegrationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageBlockingClient messageClient;
    private final MessageAsyncClient messageAsyncClient;
    private final MessageFutureClient messageFutureClient;

    public String getMessage(String messageText) {
        String responseMessage;
        try {
            responseMessage = messageClient.getMessage(messageText);
        } catch (GrpcIntegrationException e) {
            log.error("Exception while getting a message", e);
            return "Failed to send message";
        }
        return responseMessage;
    }

    public void sendWithAsyncClient(String messageText) {
        log.info("Sending message: {}", messageText);
        messageAsyncClient.getMessage(messageText, new MessageCallback(this));
    }

    public void sendWithFutureClient(String messageText) {
        log.info("Sending message: {}", messageText);
        messageFutureClient.getMessage(messageText).handle((response, throwable) -> {
            if (throwable != null) {
                log.error("Exception while getting a message", throwable);
                return null;
            }
            log.info("Received response (future): {}", response.getMessage());
            return null;
        });
    }

    public void processResponse(String responseMessage) {
        log.info("Received response (async): {}", responseMessage);
    }
}
