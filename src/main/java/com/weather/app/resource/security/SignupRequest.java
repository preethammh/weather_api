package com.weather.app.resource.security;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "User name cannot be null")
	@Schema(description = "User name")
	private String username;
	
	@NotNull(message = "Email ID name cannot be null")
	@Schema(description = "Email ID")
	private String email;
	
	@NotNull(message = "Password cannot be null")
	@Schema(description = "Password")
	private String password;
}
