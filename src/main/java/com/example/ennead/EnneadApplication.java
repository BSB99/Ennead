package com.example.ennead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EnneadApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnneadApplication.class, args);
    }

}
