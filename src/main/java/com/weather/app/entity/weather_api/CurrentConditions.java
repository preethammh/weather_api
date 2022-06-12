package com.weather.app.entity.weather_api;

import lombok.Data;

@Data
public class CurrentConditions {

    private String dayhour;
    private Temp temp;
    private String precip;
    private String humidity;
    private Wind wind;
    private String iconURL;
    private String comment;
}
