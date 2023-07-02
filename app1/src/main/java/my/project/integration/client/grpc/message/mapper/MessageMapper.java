package my.project.integration.client.grpc.message.mapper;

import my.project.gen.grpc.TestRequest;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public TestRequest toRequest(String messageText) {
        return TestRequest.newBuilder()
                .setMessage(messageText)
                .build();
    }
}
