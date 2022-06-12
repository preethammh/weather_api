package com.weather.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.app.entity.auth.User;
import com.weather.app.exception.UserSignUpException;
import com.weather.app.repository.UserRepository;
import com.weather.app.resource.security.LoginRequest;
import com.weather.app.resource.security.SignupRequest;
import com.weather.app.resource.security.UserInfoResponse;
import com.weather.app.security.UserDetailsImpl;
import com.weather.app.security.jwt.JwtUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Operation(summary = "Login using username and password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User login successfull", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserInfoResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content) })
	@PostMapping("/login")
	public ResponseEntity<UserInfoResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// get the authentication object from the user name and password
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// set the authentication
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// get the user name
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		// get the JWT cookie
		String jwtToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());

		// generate the user info response
		UserInfoResponse body = new UserInfoResponse(userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), jwtToken);

		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	@Operation(summary = "Create a new user account")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the weather details"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content) })
	@PostMapping("/signup")
	public ResponseEntity<String> authenticateUser(@Valid @RequestBody SignupRequest signUpRequest) {

		// check if user name exists
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new UserSignUpException("Username is already taken");
		}

		// check if email is already registered
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserSignUpException("Email is already in use");
		}

		// create a new user
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		// save the user
		userRepository.save(user);

		return new ResponseEntity<>("User name \"" + user.getUsername() + "\" registered successfully", HttpStatus.OK);
	}
}
