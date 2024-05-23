package com.hi.mvc2basic.scheduler.quartz;

import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TriggerController {
    private final QuartzService quartzService;

    @GetMapping("/trigger/cron")
    public String addTrigger(@RequestParam String cron){
        try {
            quartzService.addTrigger(cron);
            return cron;
        } catch (SchedulerException e) {
            return e.getMessage();
        }
    }
}
