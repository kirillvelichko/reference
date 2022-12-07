package my.project.integration.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String getIndex(){
        return "App2";
    }

    @GetMapping("/test")
    public String getTest() {
        return "test app2";
    }
}
