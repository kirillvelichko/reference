package my.project.domain.common;

import lombok.Data;

@Data
public class Host {
    private String hostname;
    private Integer port;

    public String getHttpAddress() {
        return "http://" + hostname + ":" + port;
    }
}
