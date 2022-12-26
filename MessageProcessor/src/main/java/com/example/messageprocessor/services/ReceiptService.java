package com.example.messageprocessor.services;

import com.example.messageprocessor.dto.ReceiptDto;
import com.example.messageprocessor.dto.WeatherDto;
import com.example.messageprocessor.models.Receipt;
import com.example.messageprocessor.models.Weather;
import com.example.messageprocessor.repos.ReceiptRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final ReceiptRepo receiptRepo;

    public ReceiptService(ReceiptRepo receiptRepo) {
        this.receiptRepo = receiptRepo;
    }

    public Receipt saveReceipt(ReceiptDto receiptDto){
        ModelMapper modelMapper = new ModelMapper();
        return receiptRepo.save(modelMapper.map(receiptDto, Receipt.class));
    }
}
