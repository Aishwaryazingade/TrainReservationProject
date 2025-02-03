package com.trainreservation.serviceint;

import com.trainreservation.dto.LoginRequest;
import com.trainreservation.dto.Response;
import com.trainreservation.entity.User;

public interface IUserService {
	
		
		Response login(LoginRequest loginRequest);
		
		Response register(User register);
		
		Response getAllUser();
		
		Response deleteUser(String userId);
		
		Response getUserById(String userId);
		
		
	
}
