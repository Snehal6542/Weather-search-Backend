package com.example.weather_search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;          // <-- IMPORTANT
import org.springframework.web.client.RestTemplate;      // <-- IMPORTANT


@SpringBootApplication
public class WeatherSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherSearchApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
