package com.example.paywhere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PaywhereApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaywhereApplication.class, args);
    }

}
