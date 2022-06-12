package com.weather.app.resource;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LocationRequest implements Serializable{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Location name")
	private String location;
}
