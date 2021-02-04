package com.hckj.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心
 *
 * @author ：yuhui
 * @date ：Created in 2020/7/30 17:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigCenterMain implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("ConfigCenterMain is running!");
    }
}
