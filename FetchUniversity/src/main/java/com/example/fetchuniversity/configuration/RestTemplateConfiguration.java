package com.example.fetchuniversity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean(name="restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
