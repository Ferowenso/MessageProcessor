package com.example.weathersensor;

import com.example.weathersensor.dto.WeatherDto;
import com.example.weathersensor.utils.WeatherGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedueTask {

    @Autowired
    KafkaTemplate<Long, WeatherDto> kafkaTemplate;

    @Scheduled(fixedDelay = 500L)
    public void sendMessages(){
        WeatherDto weatherDto = WeatherGenerator.weatherGenerator();
        kafkaTemplate.send("weather", weatherDto);
        log.info("Send message: " + weatherDto);
    }

}
