package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class WeatherForecast {
    @JsonProperty("location")
    private Location location;
    @JsonProperty("forecasts")
    private List<Forecast> forecasts;

    public Location getLocation() {
        return location;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
