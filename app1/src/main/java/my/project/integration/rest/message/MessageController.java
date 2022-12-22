package my.project.integration.rest.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.gen.grpc.TestRequest;
import my.project.integration.grpc.message.MessageGrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageGrpcClient messageClient;

    @GetMapping("/message")
    public String getGrpcTest() {
        var request = TestRequest.newBuilder()
                .setMessage("message from App1")
                .build();
        var response = messageClient.test(request);
        return response.getMessage();
    }
}
