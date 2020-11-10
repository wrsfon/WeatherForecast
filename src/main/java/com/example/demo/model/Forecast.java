package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Forecast {
    @JsonProperty("time")
    private Date time;
    @JsonProperty("data")
    private ForecastData data;

    public Date getTime() {
        return time;
    }

    public ForecastData getData() {
        return data;
    }
}
