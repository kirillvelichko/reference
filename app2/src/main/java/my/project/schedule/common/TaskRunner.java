package my.project.schedule.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskRunner {

    public static void runTask(Runnable task) {
        try {
            task.run();
        } catch (Exception e) {
            // for a traceid in a log message while an exception
            log.error("Error while executing a scheduled task", e);
        }
    }
}
