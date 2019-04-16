package com.zzd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ZzdApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ZzdApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 此处Application.class替换为springboot默认启动类
        return builder.sources(ZzdApplication.class);
    }
}


