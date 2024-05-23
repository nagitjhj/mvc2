package com.hi.mvc2basic.scheduler.quartz;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class QuartzService {

    private final Scheduler scheduler;

    public QuartzService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.getListenerManager().addJobListener(new MyJobListener());
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

    public JobDetail jobDetail(String triggerId){
        return JobBuilder.newJob(MyJob.class)
                .withIdentity(triggerId)
                .usingJobData("name", "정후야")
                .build();
    }

    public String addTrigger(String cron) throws SchedulerException {
        String triggerId = "junghoolee-" + System.currentTimeMillis();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

//        JobDetail job = JobBuilder.newJob(MyJob.class)
//                .withIdentity(triggerId)
//                .usingJobData("name", "정후야")
//                .build();

        JobDetail job = jobDetail(triggerId);

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerId)
                .withSchedule(cronScheduleBuilder)
//                .forJob(jobDetail())
                .build();

        scheduler.scheduleJob(job,trigger);
        return triggerId;
    }

    public void deleteTrigger(String triggerId) throws SchedulerException {
        scheduler.unscheduleJob(TriggerKey.triggerKey(triggerId));
    }

    public void updateTrigger(String triggerId, String cron) throws SchedulerException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        CronTrigger newTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerId)
                .withSchedule(cronScheduleBuilder)
                .build();
        scheduler.rescheduleJob(TriggerKey.triggerKey(triggerId), newTrigger);
    }

    public List<String> getTriggerList() throws SchedulerException {
        List<String> triggerKeyList = new ArrayList<>();
        for(TriggerKey triggerKey : scheduler.getTriggerKeys(GroupMatcher.groupEquals("DEFAULT"))){
            triggerKeyList.add(triggerKey.getName());
        }
        return triggerKeyList;
    }

    public List<String> getJobList() throws SchedulerException {
        List<String> jobKeyList = new ArrayList<>();
        for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.groupEquals("DEFAULT"))){
            jobKeyList.add(jobKey.getName());
        }
        return jobKeyList;
    }
}
