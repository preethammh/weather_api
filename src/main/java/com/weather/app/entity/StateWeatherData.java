package com.weather.app.entity;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateWeatherData {
	
	@Schema(description = "State name")
	private String stateName;
	
	@Schema(description = "List of district weather data")
	private List<DistrictWeatherData> districtWeatherData;
}
