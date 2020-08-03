package com.cczu.quatz.schedule;

import com.cczu.quatz.job.HelloJob;
import com.cczu.quatz.listener.MyJobListener;
import com.cczu.quatz.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

public class MyTriggerListenerTest {

    public static void main(String[] args) throws SchedulerException {
        // 调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 任务
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("message","打印日志")
                .usingJobData("count",0)
                .build();
        // 触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triGroup")
                .startNow()
                .usingJobData("message","打印日志2")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();
        // 让调度器关联任务和触发器
        scheduler.scheduleJob(job,trigger);
        // 创建并注册一个全局的listener
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("simpleTrigger"),EverythingMatcher.allTriggers());
        // 创建并注册一个局部的listener
        // 类似
        // 启动
        scheduler.start();
    }
}
