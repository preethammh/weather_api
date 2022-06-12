package com.weather.app.exception;

import lombok.Getter;

@Getter
public class ElementNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ElementNotFoundException() {
		
	}
	
	public ElementNotFoundException(String message) {
		this.message = message;
	}
}
