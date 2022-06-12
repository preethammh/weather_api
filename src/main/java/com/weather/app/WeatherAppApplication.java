package com.weather.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.exception.WeatherApiException;
import com.weather.app.resource.StateDistrictData;

@SpringBootApplication
public class WeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofMillis(300000)).setReadTimeout(Duration.ofMillis(300000)).build();
	}

	@Bean
	public StateDistrictData stateDistrictData() {
		ObjectMapper mapper = new ObjectMapper();
		StateDistrictData data = null;
		try {
			URL path = WeatherAppApplication.class.getResource("WeatherAppApplication.class");
			File file;
			if (path.toString().startsWith("jar:")) {
				// run in jar
				String filePath = "resources" + File.separator + "static" + File.separator
						+ "india_states_distric.json";
				file = ResourceUtils.getFile(filePath);
			} else {
				// run in IDE
				file = ResourceUtils.getFile("classpath:static/india_states_distric.json");
			}

			data = mapper.readValue(file, StateDistrictData.class);
		} catch (IOException e) {
			throw new WeatherApiException("600", "Unable to locate the district.json file");
		}
		return data;
	}
}
