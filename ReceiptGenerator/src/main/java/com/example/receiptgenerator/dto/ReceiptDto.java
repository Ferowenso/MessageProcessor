package com.example.receiptgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDto implements Serializable {

    private double amount;
    private String shop;
    private List<String> purchases;

}
