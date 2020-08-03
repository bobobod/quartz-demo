package com.cczu.quatz.listener;

import org.quartz.*;

public class MyScheduleListener implements SchedulerListener {
    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("部署时调用");
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("卸载时调用");

    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("trigger被移除");

    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println("trigger被暂停");
    }

    @Override
    public void triggersPaused(String s) {

    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {

    }

    @Override
    public void triggersResumed(String s) {

    }

    @Override
    public void jobAdded(JobDetail jobDetail) {

    }

    @Override
    public void jobDeleted(JobKey jobKey) {

    }

    @Override
    public void jobPaused(JobKey jobKey) {

    }

    @Override
    public void jobsPaused(String s) {

    }

    @Override
    public void jobResumed(JobKey jobKey) {

    }

    @Override
    public void jobsResumed(String s) {

    }

    @Override
    public void schedulerError(String s, SchedulerException e) {

    }

    @Override
    public void schedulerInStandbyMode() {

    }

    @Override
    public void schedulerStarted() {

    }

    @Override
    public void schedulerStarting() {

    }

    @Override
    public void schedulerShutdown() {

    }

    @Override
    public void schedulerShuttingdown() {

    }

    @Override
    public void schedulingDataCleared() {

    }
}
