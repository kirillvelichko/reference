package my.project.integration.client.grpc.message.exception;

public class GrpcIntegrationException extends Exception {

    public GrpcIntegrationException(Throwable cause) {
        super(cause);
    }

    public GrpcIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
