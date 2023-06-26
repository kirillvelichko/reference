package my.project.integration.client.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    @JsonProperty("userList")
    private List<User> userList;

    public String toString() {
        return userList.stream().map(User::toString).collect(Collectors.joining(","));
    }
}
