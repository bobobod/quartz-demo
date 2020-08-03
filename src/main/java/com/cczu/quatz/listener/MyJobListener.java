package com.cczu.quatz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {
    @Override
    public String getName() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("获取监听器名称");
        return this.getClass().getSimpleName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println(jobExecutionContext.getJobDetail().getKey().getName());
        System.out.println("schedule将要被执行时调用");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println(jobExecutionContext.getJobDetail().getKey().getName());
        System.out.println("schedule将被执行但是被否决了");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println(jobExecutionContext.getJobDetail().getKey().getName());
        System.out.println("schedule被执行后的结果");
    }
}
