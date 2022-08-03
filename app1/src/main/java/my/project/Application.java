package my.project;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import my.project.integration.grpc.gen.TestRequest;
import my.project.integration.grpc.gen.TestResponse;
import my.project.integration.grpc.gen.TestServiceGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9092)
                .usePlaintext()
                .build();

        TestServiceGrpc.TestServiceBlockingStub stub = TestServiceGrpc.newBlockingStub(channel);
        TestResponse testResponse = stub.test(TestRequest.newBuilder()
                .setFirstName("Kirill")
                .build());
        System.out.println(testResponse);
        channel.shutdown();
    }
}