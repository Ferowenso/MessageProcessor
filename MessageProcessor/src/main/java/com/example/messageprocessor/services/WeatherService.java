package com.example.messageprocessor.services;

import com.example.messageprocessor.dto.WeatherDto;
import com.example.messageprocessor.models.Weather;
import com.example.messageprocessor.repos.WeatherRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherRepo weatherRepo;

    public WeatherService(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    public Weather saveWeather(WeatherDto weatherDto){
        ModelMapper modelMapper = new ModelMapper();
        return weatherRepo.save(modelMapper.map(weatherDto, Weather.class));
    }

}
