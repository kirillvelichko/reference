package my.project.integration.client.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    @JsonProperty("userList")
    private List<User> userList;
}
