package domain;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Inicio {
    public static void main (String [ ] args) throws Exception{
        JobDetail job = JobBuilder.newJob(Despertador.class)
                .withIdentity("dummyJobName", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 0/60 * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
