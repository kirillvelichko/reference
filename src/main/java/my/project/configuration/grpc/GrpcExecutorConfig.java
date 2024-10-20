package my.project.configuration.grpc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
public class GrpcExecutorConfig {
    private final BeanFactory beanFactory;

    @Bean
    public Executor grpcExecutor() {
        var threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("grpc-exec-%d")
                .build();
        return new LazyTraceExecutor(beanFactory, Executors.newCachedThreadPool(threadFactory));
    }
}