package com.example.demo.service;

import com.example.demo.model.HistoricalForecast30Days;
import com.example.demo.model.WeatherForecastList;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class WeatherForecastService {
    private final RestTemplate restTemplate;
    private final String ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjRjZTE4NDg3ZDhlYTcxYjA0MmFkMzhiMWY2ZjMyMTEwMWRjNzJhZDNkNjVkMGE5NTk2MDM0MDk0YWZjZDk5MzZiMTQzNmIzMTQ3ZGE0YTNlIn0.eyJhdWQiOiIyIiwianRpIjoiNGNlMTg0ODdkOGVhNzFiMDQyYWQzOGIxZjZmMzIxMTAxZGM3MmFkM2Q2NWQwYTk1OTYwMzQwOTRhZmNkOTkzNmIxNDM2YjMxNDdkYTRhM2UiLCJpYXQiOjE2MDQ5NzE1MTIsIm5iZiI6MTYwNDk3MTUxMiwiZXhwIjoxNjM2NTA3NTEyLCJzdWIiOiIxMDgwIiwic2NvcGVzIjpbXX0.knZvRlWmVFlXoD3_y7hC5FpeC_JUYXBmRFtrztBEPXg-wAoaIqX3OD4bDeSTsg3GlxsNBW9p7PZeIQGhhDD9MgrLnRc5KTsggYWVZijr9mHLLPd61g3Z7IXqwIU9O2DmetULDAd_e5-qfdO9QktiHgVO-NP-Ql-uo3LQEGt81GHUyJ-eQUOo0jHG7oFZX346bhCA3MTLRKf9sO-Fcb9G_ojoPHaTQlFoWRFzU1Pivf04QMIC_hqi5yzS-zV6OTDNfOvOaYFbrwNq2F1MY-Bihz5zNSmAf2AswgtntpSBmLAXlyBTivkcKd0NT1eExcDsFch1AfAzUm8HbdJKa4FmibD5ztoWbHYdRq1bMjf-9t35iwYdMFj334Q-gFmlmiSjidXhFShk-jlHczamQiBzDknLMxDFX6d2IiLQKPEHOQq3jOy1LAMfurVaIVRQ-EZRlL-cM_fBHJGUnZliQPsRZ347PnHQXtG1-hixUiIXOQrfpfstmfipimOpONfuCQGIDCItlJlK0NVx6YDwSdKpQujXbGTRfHpBI3wVvUsx0CB2eNX-PbiH4xHSglzEwhK3NsN6-Lg4YdsvdjrDziaNdTYW3V_bQsnXv8evYfAU7nmdgdn-6jvQf5J7hElDABSwVzbY4NJVM3-s-Uvilwt8MwqlZ_-UO14PYmi97KIFJ20";
    private final String APIKEY = "6532d6454b8aa370768e63d6ba5a832e";
    private final Map<String, String> REGION_GEOCODE = Map.of("C","Bangkok:13.753958,100.501746", "N", "Chiang Mai:18.790219,98.987038",
            "NE", "Khon Kaen:16.4710597824,102.819856721", "E", "Chachoengsao:13.4499982,101.5999976",
            "S", "Surat Thani:9.14011,99.33311", "W", "Kanchanaburi:14.0,99.499998");


    public WeatherForecastService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherForecastList getForecastDailyByRegionAndDate(String region, String date) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("https://data.tmd.go.th/nwpapi/v1/forecast/location/daily/region")
                .queryParam("region", region)
                .queryParam("date", date);

        ResponseEntity<WeatherForecastList> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                HttpMethod.GET, request, WeatherForecastList.class);

        return responseEntity.getBody();
    }

    public HistoricalForecast30Days getHistoricalForecast30Days(String region) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("https://api.weather.com/v3/wx/conditions/historical/dailysummary/30day")
                .queryParam("apiKey", APIKEY)
                .queryParam("geocode", REGION_GEOCODE.get(region).split(":")[1])
                .queryParam("language", "en-US")
                .queryParam("format", "json")
                .queryParam("units", "e");

        HistoricalForecast30Days result = restTemplate.getForObject(uriComponentsBuilder.toUriString(), HistoricalForecast30Days.class);
        return result;
    }

    public String getProvinceByRegion(String region) {
        return REGION_GEOCODE.get(region).split(":")[0];
    }
}
