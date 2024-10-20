package my.project.configuration.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class AsyncConfig implements AsyncConfigurer {
    private final BeanFactory beanFactory;

    @Override
    public Executor getAsyncExecutor() {
        var threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("async-exec-%d")
                .build();
        var executor = Executors.newCachedThreadPool(threadFactory);
        return new LazyTraceExecutor(beanFactory, executor);
    }
}
