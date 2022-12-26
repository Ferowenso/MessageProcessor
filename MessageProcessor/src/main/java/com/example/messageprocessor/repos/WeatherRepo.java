package com.example.messageprocessor.repos;

import com.example.messageprocessor.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<Weather, Long> {
}
