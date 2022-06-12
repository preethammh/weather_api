package com.weather.app.entity;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Day implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Day in a week with possibilty of rain")
	private String day;
	
	@Schema(description = "Weather prediction on that day")
	private String weather;
}
