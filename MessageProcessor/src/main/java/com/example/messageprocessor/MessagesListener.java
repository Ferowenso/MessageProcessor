package com.example.messageprocessor;

import com.example.messageprocessor.dto.ReceiptDto;
import com.example.messageprocessor.dto.WeatherDto;
import com.example.messageprocessor.services.ReceiptService;
import com.example.messageprocessor.services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
public class MessagesListener {

    private final WeatherService weatherService;
    private final ReceiptService receiptService;

    public MessagesListener(WeatherService weatherService, ReceiptService receiptService) {
        this.weatherService = weatherService;
        this.receiptService = receiptService;
    }

    @KafkaListener(topics = "receipt", containerFactory = "receiptDtoConcurrentKafkaListenerContainerFactory")
    public void receiptListener(@Payload ReceiptDto msg) {
        log.info("Recieved receipt: " + msg);
        receiptService.saveReceipt(msg);
        log.info("Save receipt: " + msg);
    }

    @KafkaListener(topics="weather", containerFactory = "weatherDtoConcurrentKafkaListenerContainerFactory")
    public void weatherListener(@Payload WeatherDto msg){
        log.info("Recieved weather: " + msg);
        weatherService.saveWeather(msg);
        log.info("Save weather: " + msg);
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }
}
