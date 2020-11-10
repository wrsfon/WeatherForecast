package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class WeatherForecastList {
    @JsonProperty("WeatherForecasts")
    private List<WeatherForecast> weatherForecasts;

    public List<WeatherForecast> getWeatherForecasts() {
        return weatherForecasts;
    }
}
