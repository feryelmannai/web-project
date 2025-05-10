package com.spoon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.spoon.demo.model")
public class spoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(spoonApplication.class, args);
    }
}
