package my.project.integration.grpc.message.exception;

public class SendingMessageException extends Exception {

    public SendingMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
