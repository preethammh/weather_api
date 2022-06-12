package com.weather.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.weather.app.entity.Day;
import com.weather.app.entity.StateWeatherData;
import com.weather.app.entity.WeatherData;
import com.weather.app.service.WeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@Operation(summary = "Get the weather data for a particular location", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the weather details", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = WeatherData.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid location", content = @Content) })
	@GetMapping("/api/weatherData/{location}")
	public ResponseEntity<WeatherData> getWeatherDataFromLocation(@PathVariable String location) {

		// get the weather data
		WeatherData data = weatherService.getWeatherData(location);

		// send the new response entity
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@Operation(summary = "Find the most probable rainy day in a week", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the rainy day", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Day.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid location", content = @Content),
			@ApiResponse(responseCode = "404", description = "Rainy day not found", content = @Content) })
	@GetMapping("/api/rainyDay/{location}")
	public ResponseEntity<Day> getMostProbableRainyDay(@PathVariable String location) {

		// get the weather data
		Day rainyDay = weatherService.findRainyDay(location);

		// send the new response entity
		return new ResponseEntity<>(rainyDay, HttpStatus.OK);
	}

	@Operation(summary = "Find the sunny day in a week", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the sunny day", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Day.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid location", content = @Content),
			@ApiResponse(responseCode = "404", description = "Sunny day not found", content = @Content) })
	@GetMapping("/api/sunnyDay/{location}")
	public ResponseEntity<Day> getSunnyDay(@PathVariable String location) {

		// get the weather data
		Day sunnyDay = weatherService.findSunnyDay(location);

		// send the new response entity
		return new ResponseEntity<>(sunnyDay, HttpStatus.OK);
	}

	@Operation(summary = "Feth weather data of all district in a state. This data is available only for Indian states", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the district weather data", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StateWeatherData.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid location", content = @Content),
			@ApiResponse(responseCode = "404", description = "State not found. Supports only Indian states", content = @Content) })
	@GetMapping("/api/stateWeatherData/{state}")
	public ResponseEntity<StateWeatherData> fetchDistrictData(@PathVariable String state) {

		StateWeatherData data = weatherService.findStateWeatherData(state);

		// send the new response entity
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
