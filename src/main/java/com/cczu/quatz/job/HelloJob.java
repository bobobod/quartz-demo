package com.cczu.quatz.job;

import org.quartz.*;

import java.text.SimpleDateFormat;

@PersistJobDataAfterExecution // 多次调用job的时候，会对job持久化 有状态job
public class HelloJob implements Job {
    private String message;
    private Integer count;
    // quartz在初始化时会自动调用setter方法，如果遇到同名的key trigger会覆盖jobDetail中的key
    public void setMessage(String message) {
        this.message = message;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public HelloJob() {
        // 需要无参构造器
        System.out.println("hello init");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("hello world");
        // 获取jobDetail
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println(key.getName() + "--" + key.getGroup());
        System.out.println(jobExecutionContext.getJobDetail().getJobClass().getName());
        // 获取jobDataMap
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String message1 = dataMap.getString("message");
        System.out.println(message1);
        // 获取trigger中的数据
        JobDataMap dataMap1 = jobExecutionContext.getTrigger().getJobDataMap();
        String message2 = dataMap1.getString("message");
        System.out.println(message2);
        // 获取trigger中的内容
        TriggerKey key1 = jobExecutionContext.getTrigger().getKey();
        System.out.println(key1.getName() + "--" + key1.getGroup());
        // 获取jobKey
        System.out.println(jobExecutionContext.getTrigger().getJobKey().getName() + "--" + jobExecutionContext.getTrigger().getJobKey().getGroup());
        // 获取开始时间和结束时间
//        System.out.println(dateFormat.format(jobExecutionContext.getTrigger().getStartTime()) + "--" + dateFormat.format(jobExecutionContext.getTrigger().getEndTime()));
        // 获取当前任务执行时间
//        System.out.println(dateFormat.format(jobExecutionContext.getFireTime()));
        // 下一个任务执行时间
//        System.out.println(dateFormat.format(jobExecutionContext.getNextFireTime()));
        //
        System.out.println(message);
        System.out.println(count);
        count++;
        jobExecutionContext.getJobDetail().getJobDataMap().put("count",count);
    }
}
