package com.example.messageprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto implements Serializable {
    private int temp;
    private int humidity;
    private int wind_speed;
    private int precipitation;
    private double lat;
    private double lon;
}
