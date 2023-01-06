package my.project.domain.user;

import lombok.RequiredArgsConstructor;
import my.project.integration.rest.user.UserRestClient;
import my.project.integration.rest.user.request.UserRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRestClient userClient;

    public String getUsers() {
        var request = new UserRequest();
        request.setFirstName("Ivan");
        request.setLastName("Ivanov");
        var response = userClient.getUsers(request);
        return response.orElseThrow().toString();
    }
}
