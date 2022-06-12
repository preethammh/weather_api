package com.weather.app.service;

import com.weather.app.entity.Day;
import com.weather.app.entity.StateWeatherData;
import com.weather.app.entity.WeatherData;

public interface WeatherService {

	public WeatherData getWeatherData(String location);
	
	public Day findRainyDay(String location);
	
	public Day findSunnyDay(String location);

	public StateWeatherData findStateWeatherData(String stateName);
}
