package com.weather.app.entity.weather_api;

import lombok.Data;

@Data
public class NextDay {

    private String day;
    private String comment;
    private MaxTemp max_temp;
    private MinTemp min_temp;
    private String iconURL;

}
