package com.hi.mvc2basic.scheduler.quartz;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuartzService {

    private final Scheduler scheduler;

    public QuartzService(Scheduler scheduler) {
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
