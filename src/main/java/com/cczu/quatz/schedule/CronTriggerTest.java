package com.cczu.quatz.schedule;

import com.cczu.quatz.job.JobCronTrigger;
import com.cczu.quatz.job.JobSimpleTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CronTriggerTest {
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
        JobDetail job = JobBuilder.newJob(JobCronTrigger.class)
                .withIdentity("job1", "group1")
                .usingJobData("message","打印日志")
                .build();
        // 触发器
        CronTrigger trigger =  TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triGroup")
//                .startAt(startDate)
//                .endAt(endDate) // 结束时间比时间间隔更重要
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        // 让调度器关联任务和触发器
        Date date = scheduler.scheduleJob(job, trigger);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        // 启动
        scheduler.start();
        System.out.println("调度器执行时间"+dateFormat.format(date));
        // 挂起
        scheduler.standby();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.start();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭
        // 为ture 等待所有job执行结束后再关闭
        scheduler.shutdown(true);
    }
}
