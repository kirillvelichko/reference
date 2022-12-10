package my.project.integration.rest;

import lombok.RequiredArgsConstructor;
import my.project.database.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public String getIndex(){
        return "App2";
    }

    @GetMapping("/test")
    public String getTest() {
        return "test app2";
    }

    @GetMapping("/db")
    public String getDatabaseTest() {
        return userRepository.getAllByFirstName("Ivan").stream()
                .findAny()
                .orElseThrow()
                .toString();
    }
}
