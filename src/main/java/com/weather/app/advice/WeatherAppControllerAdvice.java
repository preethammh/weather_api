package com.weather.app.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

import com.weather.app.exception.ElementNotFoundException;
import com.weather.app.exception.StateDistrictLoadFailureException;
import com.weather.app.exception.UserSignUpException;
import com.weather.app.resource.ErrorResponse;

@ControllerAdvice
public class WeatherAppControllerAdvice {

	@ExceptionHandler(UserSignUpException.class)
	public ResponseEntity<ErrorResponse> handleSignUpException(UserSignUpException ex, WebRequest request) {
		return new ResponseEntity<>(getErrorResponse(ex.getErrorMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNytApiException(ElementNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(getErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StateDistrictLoadFailureException.class)
	public ResponseEntity<ErrorResponse> handleStateDistrictLoadFailure(StateDistrictLoadFailureException ex,
			WebRequest request) {
		return new ResponseEntity<>(getErrorResponse(ex.getErrorMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleRequestBodyError(MethodArgumentNotValidException ex,
			WebRequest request) {
		return new ResponseEntity<>(getErrorResponse(ex.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<ErrorResponse> handleWeatherApiError(RestClientException ex,
			WebRequest request) {
		return new ResponseEntity<>(getErrorResponse("Error occured in the Public API " + ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnknown(Exception ex, WebRequest request) {
		return new ResponseEntity<>(getErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	private ErrorResponse getErrorResponse(String errorMessage) {
		return new ErrorResponse("ERROR", errorMessage);
	}
}
