package com.example.fetchuniversity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FetchUniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(FetchUniversityApplication.class, args);
    }

}
