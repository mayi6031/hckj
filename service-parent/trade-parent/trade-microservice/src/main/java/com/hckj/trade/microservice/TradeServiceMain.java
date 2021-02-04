package com.hckj.trade.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 交易微服务
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/4 14:59
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.hckj.common.feign"})
@ComponentScan(basePackages = {"com.hckj"})
public class TradeServiceMain implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TradeServiceMain.class);

    public static void main(String[] args) {
        SpringApplication.run(TradeServiceMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("TradeServiceMain is running!");

    }

}
