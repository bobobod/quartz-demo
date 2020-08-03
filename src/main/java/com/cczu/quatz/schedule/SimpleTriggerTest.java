package com.cczu.quatz.schedule;

import com.cczu.quatz.job.HelloJob;
import com.cczu.quatz.job.JobSimpleTrigger;
import com.cczu.quatz.listener.MyJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

import java.util.Date;

public class SimpleTriggerTest {
    public static void main(String[] args) throws SchedulerException {
        // 调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 开始时间推辞3秒
        Date startDate = new Date();
        startDate.setTime(startDate.getTime()+3000);
        // 结束时间推辞10秒
        Date endDate = new Date();
        endDate.setTime(endDate.getTime()+10000);
        // 任务
        JobDetail job = JobBuilder.newJob(JobSimpleTrigger.class)
                .withIdentity("job1", "group1")
                .usingJobData("message","打印日志")
                .build();
        // 触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triGroup")
                .startAt(startDate)
                .endAt(endDate) // 结束时间比时间间隔更重要
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(3)) //5秒执行一次 执行4次
                .build();
        // 让调度器关联任务和触发器
        scheduler.scheduleJob(job,trigger);

        // 启动
        scheduler.start();
    }
}
