package my.project.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.integration.client.rest.user.UserClient;
import my.project.integration.client.rest.user.request.UserRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserClient userClient;

    public String getUser(String firstName, String lastName) {
        var request = new UserRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        var response = userClient.getUsers(request);
        return response.orElseThrow().toString();
    }

    @Async
    public CompletableFuture<String> getUserAsync(String firstName, String lastName) {
        log.info("Getting user");
        var request = new UserRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        var response = userClient.getUsers(request);
        return CompletableFuture.completedFuture(response.orElseThrow().toString());
    }
}
