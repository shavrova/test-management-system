package com.test.management.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.test.management.system", "feature.generator"})
public class TestManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestManagementSystemApplication.class, args);


    }

}
