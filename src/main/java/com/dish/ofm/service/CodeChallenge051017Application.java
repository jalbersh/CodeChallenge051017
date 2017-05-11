package com.dish.ofm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CodeChallenge051017Application {
    private static Logger LOGGER = LoggerFactory.getLogger(CodeChallenge051017Application.class);

    public static void main(String args[]) {
        SpringApplication.run(CodeChallenge051017Application.class, args);
        LOGGER.info("Finished CodeChallenge051017Application initialization...");
    }
}
