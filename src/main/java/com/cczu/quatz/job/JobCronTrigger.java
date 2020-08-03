package com.cczu.quatz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;

public class JobCronTrigger implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        System.out.println("job Cron Trigger");
        System.out.println(dateFormat.format(jobExecutionContext.getFireTime()));
    }
}
