package com.example.weathersensor;

import com.example.weathersensor.dto.WeatherDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableKafka
@EnableScheduling
@SpringBootApplication
public class WeatherSensorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherSensorApplication.class, args);
    }

}
