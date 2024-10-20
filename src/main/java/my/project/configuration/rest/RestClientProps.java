package my.project.configuration.rest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("rest.client")
@Data
public class RestClientProps {
    private Host app2;

    public String getUserListUrl() {
        return app2.getHttpAddress() + "/user/get/list";
    }
}
