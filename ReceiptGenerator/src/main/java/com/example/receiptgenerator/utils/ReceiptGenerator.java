package com.example.receiptgenerator.utils;

import com.example.receiptgenerator.dto.ReceiptDto;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReceiptGenerator {

    static final Random r = new Random();

    public static ReceiptDto receiptGenerator(){
        List<String> purchases = new ArrayList<>();
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setAmount(30000 * r.nextDouble());
        receiptDto.setShop(ReceiptGenerator.getRandomString(6));
        for(int i = 0; i<(r.ints(1, 20).findFirst().getAsInt()); i++){
            purchases.add(ReceiptGenerator.getRandomString(6));
        }
        receiptDto.setPurchases(purchases);
        return receiptDto;
    }

    private static String getRandomString(int size) {
        String AllCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(size);
        int length = AllCharacters.length();
        for (int i = 0; i < size; i++) {
            sb.append(AllCharacters.charAt((int)(length * Math.random())));
        }
        return sb.toString();
    }

}
