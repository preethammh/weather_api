package com.weather.app.exception;

import lombok.Getter;

@Getter
public class UserSignUpException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public UserSignUpException() {
		
	}
	
	public UserSignUpException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
