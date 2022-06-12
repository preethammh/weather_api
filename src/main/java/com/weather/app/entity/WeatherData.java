package com.weather.app.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WeatherData {

	@Schema(description = "Region")
	private String region;
	
	@Schema(description = "Date time")
	private String dayHour;
	
	@Schema(description = "Temperature in degree celcius")
	private String temperature;
	
	@Schema(description = "Precipitation")
	private String precipitation;
	
	@Schema(description = "Humidity")
	private String humidity;
	
	@Schema(description = "Current weather")
	private String weather;
}
