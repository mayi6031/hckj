package com.hckj.product.microservice;

import com.hckj.common.mq.rabbitmq.support.EnableRabbitmqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 产品微服务
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/4 14:59
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.hckj.common.feign"})
@ComponentScan(basePackages = {"com.hckj"})
@EnableRabbitmqMessage
public class ProductServiceMain implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceMain.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("ProductServiceMain is running!");
    }

}
