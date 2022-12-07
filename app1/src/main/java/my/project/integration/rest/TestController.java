package my.project.integration.rest;

import lombok.RequiredArgsConstructor;
import my.project.gen.grpc.TestRequest;
import my.project.integration.grpc.TestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestClient testClient;

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
        var request = TestRequest.newBuilder()
                .setMessage("test")
                .build();
        var response = testClient.test(request);
        return response.getMessage();
    }
}
