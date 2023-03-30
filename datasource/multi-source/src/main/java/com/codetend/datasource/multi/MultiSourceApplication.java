package com.codetend.datasource.multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.codetend.common", "com.codetend.datasource"})
@EnableDiscoveryClient
public class MultiSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiSourceApplication.class, args);
    }
}

