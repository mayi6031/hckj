package com.hckj.task.controller;

import com.hckj.common.web.DataResponse;
import com.hckj.task.service.DynamicSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态调度
 *
 * @author ：yuhui
 */
@RestController
public class TaskController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DynamicSchedule dynamicSchedule;

    @PostMapping("/schedule")
    public DataResponse<String> schedule(String businessCode, String cron, String printStr) {
        logger.info("schedule,businessCode:{},cron：{}，printStr：{}", businessCode, cron, printStr);
        dynamicSchedule.startJob(businessCode, cron, new Runnable() {
            @Override
            public void run() {
                logger.info("--" + printStr);
            }
        });
        return DataResponse.ok("ok");
    }

    @PostMapping("/schedule/stop")
    public DataResponse<String> scheduleStop(String businessCode) {
        logger.info("scheduleStop,businessCode：{}", businessCode);
        dynamicSchedule.stopJob(businessCode);
        return DataResponse.ok("ok");
    }

    @PostMapping("/schedule/modify")
    public DataResponse<String> scheduleModify(String businessCode, String cron, String printStr) {
        logger.info("scheduleModify,businessCode:{},cron：{}，printStr：{}", businessCode, cron, printStr);
        dynamicSchedule.modifyJob(businessCode, cron, new Runnable() {
            @Override
            public void run() {
                logger.info("--" + printStr);
            }
        });
        return DataResponse.ok("ok");
    }
}
