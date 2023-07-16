package my.project.integration.client.grpc.message.callback;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.domain.message.MessageService;
import my.project.gen.grpc.MessageResponse;

@RequiredArgsConstructor
@Slf4j
public class MessageCallback implements StreamObserver<MessageResponse> {
    private final MessageService messageService;

    @Override
    public void onNext(MessageResponse response) {
        messageService.processResponse(response.getMessage());
    }

    @Override
    public void onError(Throwable e) {
        log.error("Exception while getting a message", e);
    }

    @Override
    public void onCompleted() {
        log.info("Completed");
    }
}
