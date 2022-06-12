package com.weather.app.resource.security;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Username cannot be null")
	@Schema(description = "User name")
	private String username;
	
	@NotNull(message = "Password cannot be null")
	@Schema(description = "Password")
	private String password;
}
