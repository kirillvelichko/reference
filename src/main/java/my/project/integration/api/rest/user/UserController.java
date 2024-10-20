package my.project.integration.api.rest.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.domain.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/get/text")
    public String getUser() {
        return userService.getUser("Ivan", "Ivanov");
    }

    @GetMapping("/users/get/text")
    public String getUsers() throws ExecutionException, InterruptedException {
        var user1 = userService.getUserAsync("Ivan", "Ivanov");
        var user2 = userService.getUserAsync("Ivan", "Ivanov");
        CompletableFuture.allOf(user1, user2).join();
        log.debug("All tasks have been completed");
        return user1.get() + " " + user2.get();
    }
}
