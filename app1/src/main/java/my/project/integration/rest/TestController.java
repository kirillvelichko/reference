package my.project.integration.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.gen.grpc.TestRequest;
import my.project.integration.grpc.TestGrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final TestGrpcClient testGrpcClient;

    @GetMapping("/")
    public String getIndex() {
        return "App1";
    }

    @GetMapping("/test")
    public String getTest() {
        return "test app1";
    }

    @GetMapping("/grpc")
    public String getGrpcTest() {
        log.info("Invoked rest url: /grpc");
        var request = TestRequest.newBuilder()
                .setMessage("test")
                .build();
        var response = testGrpcClient.test(request);
        log.info("Received rpc response: " + response);
        return response.getMessage();
    }
}
