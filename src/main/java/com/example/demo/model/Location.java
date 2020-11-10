package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;

@Entity
public class Location {
    @JsonProperty("province")
    private String province;
    @JsonProperty("areatype")
    private String areatype;
    @JsonProperty("tambon")
    private String tambon;
    @JsonProperty("region")
    private String region;
    @JsonProperty("geocode")
    private int geocode;
    @JsonProperty("amphoe")
    private String amphoe;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;

    public String getProvince() {
        return province;
    }

    public String getAreatype() {
        return areatype;
    }

    public String getTambon() {
        return tambon;
    }

    public String getRegion() {
        return region;
    }

    public int getGeocode() {
        return geocode;
    }

    public String getAmphoe() {
        return amphoe;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
