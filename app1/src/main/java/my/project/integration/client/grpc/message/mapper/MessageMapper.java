package my.project.integration.client.grpc.message.mapper;

import my.project.gen.grpc.MessageRequest;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageRequest toRequest(String messageText) {
        return MessageRequest.newBuilder()
                .setText(messageText)
                .build();
    }
}
