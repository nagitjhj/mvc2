package com.hi.mvc2basic.scheduler.quartz;

import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TriggerController {
    private final QuartzService quartzService;

    @GetMapping("/trigger/cron")
    public String addTrigger(@RequestParam String cron){
        //0 21 17 23 5 ? 2024
        try {
            String triggerId = quartzService.addTrigger(cron);
            return triggerId;
        } catch (SchedulerException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/trigger/update")
    public String updateTrigger(@RequestParam Map<String, String> map){
        try {
            quartzService.updateTrigger(map.get("triggerId"), map.get("cron"));
            return map.get("triggerId");
        } catch (SchedulerException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/trigger/delete")
    public String deleteTrigger(@RequestParam String triggerId){
        try {
            quartzService.deleteTrigger(triggerId);
            return triggerId;
        } catch (SchedulerException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/trigger/all")
    public List<String> getTriggerList() throws SchedulerException {
        return quartzService.getTriggerList();
    }
}
