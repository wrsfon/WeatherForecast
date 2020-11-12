package com.example.demo.controller;

import com.example.demo.model.HistoricalForecast30Days;
import com.example.demo.model.WeatherForecastList;
import com.example.demo.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

import static com.example.demo.utils.DateUtils.dateToString;
import static com.example.demo.utils.DateUtils.plusNineDays;

@Controller
@EnableAutoConfiguration
public class MainController {
    @Autowired
    private WeatherForecastService weatherForecastService;

    @RequestMapping(value = "/weather-forecast", method = RequestMethod.GET)
    public String main(@RequestParam(defaultValue = "C") String region, @RequestParam(required = false) String date, ModelMap model){
        String today = dateToString(new Date());
        date = date == null ? today : date;

        WeatherForecastList result = weatherForecastService.getForecastDailyByRegionAndDate(region, date);

        model.addAttribute("weatherForecasts", result.getWeatherForecasts());
        model.addAttribute("region", region);
        model.addAttribute("date", date);
        model.addAttribute("minDate", today);
        model.addAttribute("maxDate", dateToString(plusNineDays(today)));
        return "home";
    }

    @RequestMapping(value = "/weather-forecast/historical", method = RequestMethod.GET)
    public String historicalForecast(@RequestParam(defaultValue = "C") String region, ModelMap model) {
        HistoricalForecast30Days historicalForecast30Days = weatherForecastService.getHistoricalForecast30Days(region);

        model.addAttribute("historicalForecast30Days", historicalForecast30Days);
        model.addAttribute("province", weatherForecastService.getProvinceByRegion(region));
        model.addAttribute("region", region);
        return "historical";
    }
}
