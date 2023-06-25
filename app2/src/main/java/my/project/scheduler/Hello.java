package my.project.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.domain.hello.HelloService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static my.project.scheduler.common.CronExpression.EVERY_5_MINUTES;
import static my.project.scheduler.common.TaskRunner.runTask;

@Component
@Slf4j
@RequiredArgsConstructor
public class Hello {
    private final HelloService helloService;

    @Scheduled(cron = EVERY_5_MINUTES)
    @SchedulerLock(name = "hello")
    public void hello() {
        runTask(helloService::hello, "Hello task");
    }
}
