package my.project.configuration.grpc;

import io.grpc.ManagedChannel;
import io.grpc.stub.AbstractStub;
import lombok.RequiredArgsConstructor;
import my.project.configuration.rest.Host;
import my.project.gen.grpc.TestServiceGrpc;
import org.springframework.cloud.sleuth.brave.instrument.grpc.SpringAwareManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class GrpcClientConfig {
    private final GrpcClientProps clientProperties;
    private final SpringAwareManagedChannelBuilder channelBuilder;
    private final Executor grpcExecutor;

    @Bean
    public TestServiceGrpc.TestServiceBlockingStub testServiceBlockingStub() {
        return createStub(clientProperties.getApp2(), TestServiceGrpc::newBlockingStub);
    }

    @Bean
    public TestServiceGrpc.TestServiceFutureStub testServiceFutureStub() {
        return createStub(clientProperties.getApp2(), TestServiceGrpc::newFutureStub);
    }

    @Bean
    public TestServiceGrpc.TestServiceStub testServiceStub() {
        return createStub(clientProperties.getApp2(), TestServiceGrpc::newStub);
    }

    private <T extends AbstractStub<T>> T createStub(Host host, Function<ManagedChannel, T> function) {
        var managedChannel = channelBuilder
                .forAddress(host.getHostname(), host.getPort())
                .executor(grpcExecutor)
                .usePlaintext()
                .build();
        return function.apply(managedChannel);
    }
}
