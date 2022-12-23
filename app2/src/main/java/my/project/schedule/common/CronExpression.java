package my.project.schedule.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CronExpression {
    public static final String EVERY_HOUR = "0 0 * ? * *";
    public static final String EVERY_10_MINUTES = "0 0/10 * ? * *";
}