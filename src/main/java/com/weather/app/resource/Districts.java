package com.weather.app.resource;

import java.util.List;

import lombok.Data;

@Data
public class Districts {

	private String state;
	private List<String> districts;
}
