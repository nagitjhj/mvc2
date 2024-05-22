package com.hi.mvc2basic.scheduler.quartz;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
public class QuartzConfig {

    private final Scheduler scheduler;

    public QuartzConfig( Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.start();
    }

//    @Bean
//    public JobDetail jobDetail(){
//        String triggerId = "hoolee-" + System.currentTimeMillis();
//        return JobBuilder.newJob(MyJob.class)
//                .withIdentity(triggerId)
////                .storeDurably()
//                .build();
//    }

    public void addTrigger(String cron) throws SchedulerException {
        String triggerId = "junghoolee-" + System.currentTimeMillis();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity(triggerId)
                .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerId)
                .withSchedule(cronScheduleBuilder)
//                .forJob(jobDetail())
                .build();

        scheduler.scheduleJob(job,trigger);
    }
}
