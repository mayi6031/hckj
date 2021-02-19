package com.hckj.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 定时任务
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/4 14:59
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.hckj.common.feign"})
@ComponentScan(basePackages = {"com.hckj"})
public class TaskServiceMain implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceMain.class);

    public static void main(String[] args) {
        SpringApplication.run(TaskServiceMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("TaskServiceMain is running!");
    }

}
