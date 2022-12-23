package my.project.schedule;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static my.project.schedule.common.CronExpression.EVERY_10_MINUTES;

@Component
@Slf4j
public class HelloMessage {

    @Scheduled(cron = EVERY_10_MINUTES)
    @SchedulerLock(name = "helloTask")
    public void helloTask() {
        log.info("Started helloTask");
        log.info("Finished helloTask");
    }
}
