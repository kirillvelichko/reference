package my.project.configuration.grpc;

import lombok.Data;
import my.project.configuration.rest.Host;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("grpc.client")
@Data
public class GrpcClientProps {
    private Host app2;
}
