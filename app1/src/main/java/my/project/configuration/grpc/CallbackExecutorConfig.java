package my.project.configuration.grpc;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
public class CallbackExecutorConfig {
    private final BeanFactory beanFactory;
    private Executor executor;

    @Bean
    public Executor callbackExecutor() {
        var threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("callback-exec-%d")
                .build();
        executor = new LazyTraceExecutor(beanFactory, Executors.newCachedThreadPool(threadFactory));
        return executor;
    }

    public <T> CompletableFuture<T> exec(ListenableFuture<T> listenableFuture) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        var callback = new FutureCallback<T>() {
            @Override
            public void onSuccess(T result) {
                completableFuture.complete(result);
            }

            @Override
            public void onFailure(@Nonnull Throwable t) {
                completableFuture.completeExceptionally(t);
            }
        };
        Futures.addCallback(listenableFuture, callback, executor);
        return completableFuture;
    }
}