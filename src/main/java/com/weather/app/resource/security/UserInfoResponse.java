package com.weather.app.resource.security;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Schema(description = "User id")
	private Long id;
	
	@Schema(description =  "Logged in user name")
	private String username;
	
	@Schema(description = "Logged in user email")
	private String email;
	
	@Schema(description = "JWT token for the logged in user")
	private String jwtToken;
}
