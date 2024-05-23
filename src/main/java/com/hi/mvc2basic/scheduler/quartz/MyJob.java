package com.hi.mvc2basic.scheduler.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

@Slf4j
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try{
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String name = dataMap.getString("name");
            log.info(name + " 건야 빨리 순산 기원 돌아와");
//            throw new RuntimeException();

        }catch (Exception e) {
            JobExecutionException ex = new JobExecutionException(e);
            int refireCount = context.getRefireCount();
            log.info("재실행 {}", refireCount);
            if(refireCount < 3){
                ex.setRefireImmediately(true);
            }else{
                ex.setRefireImmediately(false);
//                context.setResult();
            }
            Object result = context.getResult();
//            log.info(result.);
            throw ex;
        }
    }
}
