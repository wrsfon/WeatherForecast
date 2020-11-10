package com.example.demo.service;

import com.example.demo.model.WeatherForecastList;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherForecastService {
    private final RestTemplate restTemplate;
    private final String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjRjZTE4NDg3ZDhlYTcxYjA0MmFkMzhiMWY2ZjMyMTEwMWRjNzJhZDNkNjVkMGE5NTk2MDM0MDk0YWZjZDk5MzZiMTQzNmIzMTQ3ZGE0YTNlIn0.eyJhdWQiOiIyIiwianRpIjoiNGNlMTg0ODdkOGVhNzFiMDQyYWQzOGIxZjZmMzIxMTAxZGM3MmFkM2Q2NWQwYTk1OTYwMzQwOTRhZmNkOTkzNmIxNDM2YjMxNDdkYTRhM2UiLCJpYXQiOjE2MDQ5NzE1MTIsIm5iZiI6MTYwNDk3MTUxMiwiZXhwIjoxNjM2NTA3NTEyLCJzdWIiOiIxMDgwIiwic2NvcGVzIjpbXX0.knZvRlWmVFlXoD3_y7hC5FpeC_JUYXBmRFtrztBEPXg-wAoaIqX3OD4bDeSTsg3GlxsNBW9p7PZeIQGhhDD9MgrLnRc5KTsggYWVZijr9mHLLPd61g3Z7IXqwIU9O2DmetULDAd_e5-qfdO9QktiHgVO-NP-Ql-uo3LQEGt81GHUyJ-eQUOo0jHG7oFZX346bhCA3MTLRKf9sO-Fcb9G_ojoPHaTQlFoWRFzU1Pivf04QMIC_hqi5yzS-zV6OTDNfOvOaYFbrwNq2F1MY-Bihz5zNSmAf2AswgtntpSBmLAXlyBTivkcKd0NT1eExcDsFch1AfAzUm8HbdJKa4FmibD5ztoWbHYdRq1bMjf-9t35iwYdMFj334Q-gFmlmiSjidXhFShk-jlHczamQiBzDknLMxDFX6d2IiLQKPEHOQq3jOy1LAMfurVaIVRQ-EZRlL-cM_fBHJGUnZliQPsRZ347PnHQXtG1-hixUiIXOQrfpfstmfipimOpONfuCQGIDCItlJlK0NVx6YDwSdKpQujXbGTRfHpBI3wVvUsx0CB2eNX-PbiH4xHSglzEwhK3NsN6-Lg4YdsvdjrDziaNdTYW3V_bQsnXv8evYfAU7nmdgdn-6jvQf5J7hElDABSwVzbY4NJVM3-s-Uvilwt8MwqlZ_-UO14PYmi97KIFJ20";

    public WeatherForecastService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherForecastList getForecastDailyByRegionAndDate(String region, String date) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ accessToken);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("https://data.tmd.go.th/nwpapi/v1/forecast/location/daily/region")
                .queryParam("region", region)
                .queryParam("date", date);

        ResponseEntity<WeatherForecastList> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                HttpMethod.GET, request, WeatherForecastList.class);

        return responseEntity.getBody();
    }
}
