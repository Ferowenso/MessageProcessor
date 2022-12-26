package com.example.messageprocessor.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Setter
public class Receipt{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double amount;
    private String shop;

    @ElementCollection
    private List<String> purchases;


}
