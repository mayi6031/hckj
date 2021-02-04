package com.hckj.register;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterCenterMain implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RegisterCenterMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("RegisterCenterMain is running!");
    }
}
