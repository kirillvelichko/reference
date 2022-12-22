package my.project.configuration.grpc;

import io.grpc.ManagedChannel;
import io.grpc.stub.AbstractStub;
import lombok.RequiredArgsConstructor;
import my.project.configuration.rest.Host;
import my.project.gen.grpc.TestServiceGrpc;
import org.springframework.cloud.sleuth.brave.instrument.grpc.SpringAwareManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static my.project.gen.grpc.TestServiceGrpc.TestServiceBlockingStub;

@Configuration
@RequiredArgsConstructor
public class GrpcClientConfig {
    private final GrpcClientProps clientProperties;
    private final SpringAwareManagedChannelBuilder channelBuilder;

    @Bean
    public TestServiceBlockingStub testServiceBlockingStub() {
        return createStub(clientProperties.getApp2(), TestServiceGrpc::newBlockingStub);
    }

    private <T extends AbstractStub<T>> T createStub(Host host, Function<ManagedChannel, T> function) {
        var managedChannel = channelBuilder
                .forAddress(host.getHostname(), host.getPort())
                .usePlaintext()
                .build();
        return function.apply(managedChannel);
    }
}
