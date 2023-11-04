package com.nyal.minisof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.nyal.minisof.repository")
public class MinisofApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinisofApplication.class, args);
    }

}
