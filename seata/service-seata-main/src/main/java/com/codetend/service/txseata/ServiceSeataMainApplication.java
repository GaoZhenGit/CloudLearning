package com.codetend.service.txseata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.codetend.common", "com.codetend.service"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceSeataMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSeataMainApplication.class, args);
    }
}

