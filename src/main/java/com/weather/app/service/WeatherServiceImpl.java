package com.weather.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.weather.app.entity.Day;
import com.weather.app.entity.DistrictWeatherData;
import com.weather.app.entity.StateWeatherData;
import com.weather.app.entity.WeatherData;
import com.weather.app.entity.weather_api.NextDay;
import com.weather.app.entity.weather_api.PublicApiResponse;
import com.weather.app.exception.ElementNotFoundException;
import com.weather.app.resource.Districts;
import com.weather.app.resource.StateDistrictData;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private StateDistrictData stateDistrictData;

	@Override
	public WeatherData getWeatherData(String location) {

		ResponseEntity<PublicApiResponse> apiResponse = getApiResponse(location);

		// get the response body
		PublicApiResponse responseBody = apiResponse.getBody();

		if(responseBody.getStatus()!=null &&responseBody.getStatus().equals("fail")) {
			throw new ElementNotFoundException("Invalid location");
		}
		
		// create the new weather data object
		WeatherData weatherData = new WeatherData();

		// set the time
		weatherData.setDayHour(responseBody.getCurrentConditions().getDayhour());

		// set the humidity
		weatherData.setHumidity(responseBody.getCurrentConditions().getHumidity());

		// set the precipitation
		weatherData.setPrecipitation(responseBody.getCurrentConditions().getPrecip());

		// set the region
		weatherData.setRegion(responseBody.getRegion());

		// set the temperature
		weatherData.setTemperature(responseBody.getCurrentConditions().getTemp().getC() + "\u00B0 celsius");

		// set the weather
		weatherData.setWeather(responseBody.getCurrentConditions().getComment());

		return weatherData;
	}

	@Override
	public Day findRainyDay(String location) {
		// get the API response
		ResponseEntity<PublicApiResponse> apiResponse = getApiResponse(location);

		if(apiResponse.getBody().getStatus()!=null &&apiResponse.getBody().getStatus().equals("fail")) {
			throw new ElementNotFoundException("Invalid location");
		}
		
		// get the weekly weather
		List<NextDay> nextDays = apiResponse.getBody().getNext_days();

		for (NextDay nextDay : nextDays) {
			if (nextDay.getComment().toLowerCase().contains("thunderstorms")
					|| nextDay.getComment().toLowerCase().contains("rain")) {
				Day rainyDay = new Day();
				rainyDay.setDay(nextDay.getDay());
				rainyDay.setWeather(nextDay.getComment());
				return rainyDay;
			}
		}

		throw new ElementNotFoundException("No prediction of rain next week");

	}

	@Override
	public Day findSunnyDay(String location) {
		// get the API response
		ResponseEntity<PublicApiResponse> apiResponse = getApiResponse(location);

		if(apiResponse.getBody().getStatus()!=null &&apiResponse.getBody().getStatus().equals("fail")) {
			throw new ElementNotFoundException("Invalid location");
		}
		
		// get the weekly weather
		List<NextDay> nextDays = apiResponse.getBody().getNext_days();

		for (NextDay nextDay : nextDays) {
			if (nextDay.getComment().toLowerCase().contains("sunny")) {
				Day sunnyDay = new Day();
				sunnyDay.setDay(nextDay.getDay());
				sunnyDay.setWeather(nextDay.getComment());
				return sunnyDay;
			}
		}

		throw new ElementNotFoundException("No sunny day predicted next week");

	}

	// Method to get the API response
	private ResponseEntity<PublicApiResponse> getApiResponse(String location) throws RestClientException {
		// get the response from the weather API
		ResponseEntity<PublicApiResponse> apiResponse = restTemplate.exchange(
				"https://weatherdbi.herokuapp.com/data/weather/" + location.trim(), HttpMethod.GET, null,
				PublicApiResponse.class);
		return apiResponse;
	}

	@Override
	public StateWeatherData findStateWeatherData(String stateName) {

		// get all the districts
		Districts districts = stateDistrictData.getStates().stream()
				.filter(stateDistrict -> stateDistrict.getState().trim().equalsIgnoreCase(stateName.trim())).findFirst()
				.orElse(null);

		// return all the district weather data
		List<DistrictWeatherData> districtWeatherDataList = new LinkedList<>();
		if (districts != null) {
			districtWeatherDataList = districts.getDistricts().parallelStream().map(eachDistrict -> {
				WeatherData weatherData = getWeatherData(eachDistrict.replaceAll("\\s", "").split("\\(")[0]);
				return new DistrictWeatherData(eachDistrict, weatherData);
			}).collect(Collectors.toList());
		} else {
			throw new ElementNotFoundException("State name " + stateName + " not found in the database."
					+ "District wise weather data is supported only for Indian states");
		}

		return new StateWeatherData(stateName, districtWeatherDataList);
	}

}
