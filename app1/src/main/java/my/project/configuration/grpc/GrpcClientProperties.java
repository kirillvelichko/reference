package my.project.configuration.grpc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("grpc.client")
@Data
public class GrpcClientProperties {
    private Host app2;

    @Data
    public static class Host {
        private String hostname;
        private Integer port;
    }
}
