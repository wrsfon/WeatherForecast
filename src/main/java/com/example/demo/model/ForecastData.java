package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class ForecastData {
    private final List<String> weatherCondition = List.of("Clear", "Partly cloudy", "Cloudy", "Overcast", "Light rain",
            "Moderate rain", "Heavy rain", "Thunderstorm", "Very cold", "Cold", "Cool", "Very hot");

    @JsonProperty("cond")
    private int cond;
    @JsonProperty("tc")
    private double tc;
    @JsonProperty("rh")
    private double rh;

    public String getCond() {
        return weatherCondition.get(cond - 1 );
    }

    public double getTc() {
        return tc;
    }

    public double getRh() {
        return rh;
    }
}
