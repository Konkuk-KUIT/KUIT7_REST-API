package com.kuit.baemin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // BaseEntity createdAt, updatedAt 자동 주입
@SpringBootApplication
public class BaeminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaeminApplication.class, args);
    }
}
