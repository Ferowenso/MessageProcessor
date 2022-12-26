package com.example.receiptgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableKafka
@EnableScheduling
@SpringBootApplication
public class ReceiptGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiptGeneratorApplication.class, args);
    }

}
