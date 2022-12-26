package com.example.receiptgenerator;

import com.example.receiptgenerator.dto.ReceiptDto;
import com.example.receiptgenerator.utils.ReceiptGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;


@Component
@Slf4j
public class SchedueTask {

    @Autowired
    KafkaTemplate<Long, ReceiptDto> kafkaTemplate;


    @Scheduled(fixedDelay = 2000L)
    public void sendMessages() {
        ReceiptDto receiptDto = ReceiptGenerator.receiptGenerator();
        kafkaTemplate.send("receipt", receiptDto);
        log.info("Send receipt: " + receiptDto);
    }

}
