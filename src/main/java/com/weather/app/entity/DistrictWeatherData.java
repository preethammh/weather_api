package com.weather.app.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictWeatherData {
	
	@Schema(name="District name")
	private String district;
	
	@Schema(name="Weather data of a district")
	private WeatherData weatherData;
}
