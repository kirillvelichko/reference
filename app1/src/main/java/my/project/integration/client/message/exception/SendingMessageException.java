package my.project.integration.client.message.exception;

public class SendingMessageException extends Exception {

    public SendingMessageException(Throwable cause) {
        super(cause);
    }
    public SendingMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
