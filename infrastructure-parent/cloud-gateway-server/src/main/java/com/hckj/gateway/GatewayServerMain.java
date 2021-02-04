package com.hckj.gateway;

import com.hckj.gateway.filter.PostFilter;
import com.hckj.gateway.filter.PreFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 网关
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayServerMain implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerMain.class, args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("GatewayServerMain is running!");
    }
}
