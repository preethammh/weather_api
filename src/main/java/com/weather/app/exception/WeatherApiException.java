package com.weather.app.exception;


public class WeatherApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private	String errorMessage;
	
	public WeatherApiException() {
		
	}
	
	public WeatherApiException(String errorCode,String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
}
