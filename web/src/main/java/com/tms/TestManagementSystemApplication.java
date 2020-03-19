package com.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tms"})
public class TestManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestManagementSystemApplication.class, args);


    }

}
