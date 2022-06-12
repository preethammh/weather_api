package com.weather.app.entity.weather_api;

import java.util.List;

import lombok.Data;

@Data
public class PublicApiResponse {

	private String status;
    private String region;
    private CurrentConditions currentConditions;
    private List<NextDay> next_days;
    private String data_source;
}
