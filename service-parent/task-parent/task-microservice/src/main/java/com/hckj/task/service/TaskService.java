package com.hckj.task.service;

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
//    @Autowired
//    private ProductInnovateFeign productInnovateFeign;

    @Scheduled(cron = "0/3 * * * * ?")
    public void task1() {
        if (scheduleConfig.locked(Thread.currentThread().getStackTrace()[1])) {
            System.out.println(Thread.currentThread().getName() + " | task1--" + sdf.format(new Date()));
//            ProductInnovateModel productInnovateModel = productInnovateFeign.getProductInnovateInfo(1).getDataWithException();
//            System.out.println(JSON.toJSONString(productInnovateModel.getName()));
        }
    }

    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void task2() {
        if (scheduleConfig.locked(Thread.currentThread().getStackTrace()[1])) {
            System.out.println(Thread.currentThread().getName() + " | task2--------------------------------" + sdf.format(new Date()));
        }
    }

}