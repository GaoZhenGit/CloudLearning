package com.codetend.service.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.codetend.common", "com.codetend.service"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceDatabaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDatabaseApplication.class, args);
    }
}

