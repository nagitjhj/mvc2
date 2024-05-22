package com.hi.mvc2basic.scheduler.springscheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Scheduler {
    @Scheduled(cron = "10 * * * * *")
    public void run(){
        log.info("1");
    }
}
