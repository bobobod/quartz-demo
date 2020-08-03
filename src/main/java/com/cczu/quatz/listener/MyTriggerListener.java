package com.cczu.quatz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class MyTriggerListener implements TriggerListener {
    private String name;

    public MyTriggerListener(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("获取触发器监听器名称");
        return name;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String name = trigger.getKey().getName();
        System.out.println("trigger监听器被触发执行"+name);
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String name = trigger.getKey().getName();
        System.out.println("trigger监听器给了个选择否决job的执行，为ture表示不会触发执行"+name);
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("错过触发");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        System.out.println("trigger Listener 完成之后");
    }
}
