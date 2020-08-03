package com.cczu.quatz.schedule;

import com.cczu.quatz.job.HelloJob;
import com.cczu.quatz.listener.MyJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

import java.util.Date;

public class HelloTest {
    public static void main(String[] args) throws SchedulerException {
        // 调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 设置任务的开始时间和结束时间
        // 开始时间推辞3秒
        Date startDate = new Date();
        startDate.setTime(startDate.getTime()+3000);
        // 结束时间推辞10秒
        Date endDate = new Date();
        endDate.setTime(endDate.getTime()+10000);
        // 任务
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("message","打印日志")
                .usingJobData("count",0)
                .build();
        // 触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triGroup")
               // .startNow()
                .startAt(startDate)
                .endAt(endDate)
                .usingJobData("message","打印日志2")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();


        // 启动
        scheduler.start();
    }
}