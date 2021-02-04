package com.hckj.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AdminMain extends SpringBootServletInitializer {

    public static void main(String[] args) {
        Long time = System.currentTimeMillis();
        SpringApplication.run(AdminMain.class);
        System.out.println("AdminMain启动耗时：" + (System.currentTimeMillis() - time) + "===");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}