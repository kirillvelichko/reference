package my.project.integration.client.grpc.message.callback;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.gen.grpc.TestResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageCallback implements StreamObserver<TestResponse> {

    @Override
    public void onNext(TestResponse response) {
        log.info("Received message: {}", response.getMessage());
    }

    @Override
    public void onError(Throwable e) {
        log.error("Exception while processing MessageCallback, cause: {}", e.getMessage());
    }

    @Override
    public void onCompleted() {
        log.info("MessageCallback completed");
    }
}
