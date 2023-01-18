package com.codetend.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadConfig {
    @Bean
    public ExecutorService getThreadPool() {
        return Executors.newCachedThreadPool();
    }
    @Bean
    public ThreadLocal<Long> getTimeThreadLocal() {
        return new ThreadLocal<>();
    }
}
