package my.project.integration.client.grpc.message.callback;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.domain.message.MessageService;
import my.project.gen.grpc.TestResponse;

@RequiredArgsConstructor
@Slf4j
public class MessageCallback implements StreamObserver<TestResponse> {
    private final MessageService messageService;

    @Override
    public void onNext(TestResponse response) {
        messageService.processResponse(response.getMessage());
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
