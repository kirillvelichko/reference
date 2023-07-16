package my.project.integration.api.rest.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.domain.message.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/message1")
    public String getMessage1() {
        log.info(messageService.getMessage("Hello message1"));
        return "Blocking client";
    }

    @GetMapping("/message2")
    public String getMessage2() {
        messageService.sendWithAsyncClient("Hello message2");
        return "Async client";
    }

    @GetMapping("/message3")
    public String getMessage3() {
        messageService.sendWithFutureClient("Hello message3");
        return "Future client";
    }
}
