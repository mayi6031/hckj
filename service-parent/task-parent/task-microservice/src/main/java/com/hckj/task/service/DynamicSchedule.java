package com.hckj.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态调度任务管理类
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/19 14:39
 */
@Component
public class DynamicSchedule {
    private Map<String, ScheduledFuture<?>> scheduledFutureMap = new ConcurrentHashMap<>();

    @Autowired
    private TaskScheduler taskScheduler;

    /**
     * 开启任务
     *
     * @param businessCode
     * @param cron
     * @param runnable
     * @return
     */
    public ScheduledFuture<?> startJob(String businessCode, String cron, Runnable runnable) {
        if (scheduledFutureMap.containsKey(businessCode)) {
            return scheduledFutureMap.get(businessCode);
        }
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(runnable, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                return new CronTrigger(cron).nextExecutionTime(triggerContext);
            }
        });
        scheduledFutureMap.put(businessCode, scheduledFuture);
        return scheduledFuture;
    }

    /**
     * 停止任务
     *
     * @param businessCode
     * @return
     */
    public boolean stopJob(String businessCode) {
        if (scheduledFutureMap.containsKey(businessCode)) {
            ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(businessCode);
            scheduledFutureMap.remove(businessCode);
            if (scheduledFuture != null) {
                return scheduledFuture.cancel(true);
            }
        }
        return false;
    }

    /**
     * 修改任务
     *
     * @param businessCode
     * @param cron
     * @param runnable
     * @return
     */
    public ScheduledFuture<?> modifyJob(String businessCode, String cron, Runnable runnable) {
        stopJob(businessCode);
        return startJob(businessCode, cron, runnable);
    }
}
