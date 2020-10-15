package com.yang.userview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserViewApplication.class, args);
    }

}
