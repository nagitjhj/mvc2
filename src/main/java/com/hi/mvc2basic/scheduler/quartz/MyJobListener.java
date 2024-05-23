package com.hi.mvc2basic.scheduler.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Job 생명주기 관리
 */
@Slf4j
public class MyJobListener implements JobListener {
    @Override
    public String getName() {
        return "2025년 야구 짱 -> 이정후";
    }

    /**
     * Job 수행되기 전 상태
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        log.info("{} 실행됩니다", jobExecutionContext.getJobDetail());
    }

    /**
     * Job 중단된 상태
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        log.info("{} 중단중단", jobExecutionContext.getJobDetail());
    }

    /**
     * Job 수행이 완료된 상태
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        log.info("{} 완료되었습니다", jobExecutionContext.getJobDetail());
    }
}
