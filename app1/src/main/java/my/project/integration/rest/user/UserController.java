package my.project.integration.rest.user;

import lombok.RequiredArgsConstructor;
import my.project.domain.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/get/text")
    public String getUsers() {
        return userService.getUsers();
    }
}
