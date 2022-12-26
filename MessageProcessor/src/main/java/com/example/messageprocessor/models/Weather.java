package com.example.messageprocessor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
public class Weather{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int temp;
    private int humidity;
    private int wind_speed;
    private int precipitation;
    private double lat;
    private double lon;

}
