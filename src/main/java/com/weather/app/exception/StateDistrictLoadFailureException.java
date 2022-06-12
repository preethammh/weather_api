package com.weather.app.exception;

import lombok.Getter;

@Getter
public class StateDistrictLoadFailureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public StateDistrictLoadFailureException() {
		
	}
	
	public StateDistrictLoadFailureException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
