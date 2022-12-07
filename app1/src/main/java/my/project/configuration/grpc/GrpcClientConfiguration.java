package my.project.configuration.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static my.project.gen.grpc.TestServiceGrpc.TestServiceBlockingStub;
import static my.project.gen.grpc.TestServiceGrpc.newBlockingStub;

@Configuration
@RequiredArgsConstructor
public class GrpcClientConfiguration {
    private final GrpcClientProperties clientProperties;

    @Bean
    public ManagedChannel app2ManagedChannel() {
        var app2 = clientProperties.getApp2();
        return ManagedChannelBuilder.forAddress(app2.getHostname(), app2.getPort())
                .usePlaintext()
                .build();
    }

    @Bean
    public TestServiceBlockingStub testServiceBlockingStub(ManagedChannel app2ManagedChannel) {
        return newBlockingStub(app2ManagedChannel);
    }
}
