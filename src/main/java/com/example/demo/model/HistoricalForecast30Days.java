package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class HistoricalForecast30Days {
    @JsonProperty("temperatureMax")
    private List<Double> temperatureMaxs;
    @JsonProperty("temperatureMin")
    private List<Double> temperatureMins;
    @JsonProperty("validTimeLocal")
    private List<String> validTimeLocals;
    @JsonProperty("wxPhraseLongDay")
    private List<String> wxPhraseLongDays;
    @JsonProperty("wxPhraseLongNight")
    private List<String> wxPhraseLongNights;

    public List<String> getTemperatureMax() {
        return temperatureMaxs.stream().map(x -> FahrenheitToCelsius(x)).map(x -> String.format("%.02f", x)).collect(Collectors.toList());
    }

    public List<String> getTemperatureMin() {
        return temperatureMins.stream().map(x -> FahrenheitToCelsius(x)).map(x -> String.format("%.02f", x)).collect(Collectors.toList());
    }

    public List<String> getValidTimeLocal() {
        return validTimeLocals.stream().map(x -> x.substring(0,10)).collect(Collectors.toList());
    }

    public List<String> getWxPhraseLongDay() {

        return wxPhraseLongDays;
    }

    public List<String> getWxPhraseLongNight() {
        return wxPhraseLongNights;
    }

    public static final double FahrenheitToCelsius(double temp) {
        return ((temp - 32)*5)/9;
    }
}
