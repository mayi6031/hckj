package com.hckj.task.service;

import com.alibaba.fastjson.JSON;
import com.hckj.task.config.ScheduleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Autowired
    private ScheduleConfig scheduleConfig;

//    @Scheduled(fixedDelay = 1000)
//    public void task01() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + " | task01 " + sdf.format(new Date()));
//        Thread.sleep(2000);
//    }
//
//    @Scheduled(fixedDelay = 2000)
//    public void task02() {
//        System.out.println(Thread.currentThread().getName() + " | task02 " + sdf.format(new Date()));
//    }

//    @Scheduled(cron = "0/1 * * * * ?")
//    public void task011() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + " | task011 " + new Date().toLocaleString());
//        Thread.sleep(2000);
//    }

    @Scheduled(cron = "0/3 * * * * ?")
    public void task022() {
        if (scheduleConfig.locked(Thread.currentThread().getStackTrace()[1])) {
            System.out.println(Thread.currentThread().getName() + " | task022--" + sdf.format(new Date()));
        }
    }

    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void task03() {
        if (scheduleConfig.locked(Thread.currentThread().getStackTrace()[1])) {
            System.out.println(Thread.currentThread().getName() + " | task033--------------------------------" + sdf.format(new Date()));
        }
    }

}