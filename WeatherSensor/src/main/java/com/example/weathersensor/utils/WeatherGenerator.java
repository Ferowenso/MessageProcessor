package com.example.weathersensor.utils;

import com.example.weathersensor.dto.WeatherDto;

import java.util.Random;

public class WeatherGenerator {

    public static WeatherDto weatherGenerator(){
        Random r = new Random();
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setHumidity(r.nextInt(100 + 1));
        weatherDto.setLat(-90 + (90 - (-90)) * r.nextDouble());
        weatherDto.setLon(-180 + (180 - (-180)) * r.nextDouble());
        weatherDto.setTemp(-50 + r.nextInt(50 - -50 + 1));
        weatherDto.setPrecipitation(r.nextInt(100 + 1));
        weatherDto.setWind_speed(r.nextInt(12 + 1));
        return weatherDto;
    }

}
