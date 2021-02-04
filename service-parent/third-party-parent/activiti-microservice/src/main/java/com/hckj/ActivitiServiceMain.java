package com.hckj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 产品微服务
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/4 14:59
 */
@SpringCloudApplication
@ComponentScan(basePackages = {"com.hckj"})
public class ActivitiServiceMain implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiServiceMain.class);

    public static void main(String[] args) {
        SpringApplication.run(ActivitiServiceMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("ActivitiServiceMain is running!");

    }

}
