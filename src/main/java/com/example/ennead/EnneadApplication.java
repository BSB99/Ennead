package com.example.ennead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EnneadApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnneadApplication.class, args);
    }

}
