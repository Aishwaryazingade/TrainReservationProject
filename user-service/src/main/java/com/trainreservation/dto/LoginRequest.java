package com.trainreservation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
	
	
	@NotBlank(message="email cannot be empty")
	private String email;

	@NotBlank(message="`password cannot be empty")
	private String password;

}