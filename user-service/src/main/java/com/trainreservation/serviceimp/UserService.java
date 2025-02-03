package com.trainreservation.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trainreservation.dto.LoginRequest;
import com.trainreservation.dto.Response;
import com.trainreservation.dto.UserDto;
import com.trainreservation.entity.User;

import com.trainreservation.repo.UserRepository;
import com.trainreservation.serviceint.IUserService;
import com.trainreservation.utils.JWTUtils;
import com.trainreservation.utils.Utils;
import com.trainreservation.exception.ResourceNotFoundException;


@Service
public class UserService implements IUserService {

	
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		@Autowired
		private JWTUtils jwtUtils;
		
		@Autowired
		private AuthenticationConfiguration authenticationConfiguration;
		
		 public Response login(LoginRequest loginRequest) {

		        Response response = new Response();

		        try {
		        	String token="";
		        	AuthenticationManager authenticationManager=authenticationConfiguration.getAuthenticationManager();
		        
		           Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("user Not found"));
		            if (authentication.isAuthenticated()) {
		             token = jwtUtils.generateToken(user);
		            }
		            response.setStatusCode(200);
		            response.setToken(token);
		            response.setRole(user.getRole());
//		            response.setExpirationTime("7 Days");
		            response.setMessage("successful");

		        } catch (ResourceNotFoundException e) {
		            response.setStatusCode(404);
		            response.setMessage(e.getMessage());

		        } catch (Exception e) {

		            response.setStatusCode(500);
		            response.setMessage("Error Occurred During User Login ");
		        }
		        return response;
		    }

	@Override
	public Response register(User register) {
		Response response=new Response();
		try {
			if(register.getRole()==null || register.getRole().isBlank()) {
				register.setRole("USER");
			}
			if(userRepository.existsByEmail(register.getEmail())) {
				throw new ResourceNotFoundException(register.getEmail()+"Already Exist");
			}
			register.setPassword(passwordEncoder.encode(register.getPassword()));
			User savedUser=userRepository.save(register);
			UserDto userDto=Utils.mapUserEntityToUserDto(savedUser);
			response.setStatusCode(200);
			response.setUser(userDto);
			
		}catch(ResourceNotFoundException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		
			
		}catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error Occured During User Registration"+e.getMessage());
			
		}
		return response;
	}

	@Override
	public Response getAllUser() {
		Response response=new  Response();
		try {
			List<User> userList=userRepository.findAll();
			List<UserDto> userDtoList=Utils.mapUserListEntityToUserListDto(userList);
			response.setStatusCode(200);
			response.setMessage("Successfull");
			response.setUserList(userDtoList);
			
		}catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error Getting all user"+e.getMessage());
			
		}
		return response;
	}


	@Override
	public Response deleteUser(String userId) {
		Response response=new  Response();
		try {
			userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new ResourceNotFoundException("User Not found"));
			userRepository.deleteById(Long.valueOf(userId));
			response.setStatusCode(200);
			response.setMessage("Successfull");
			
			
		}catch(ResourceNotFoundException e) {
			response.setStatusCode(404);
			response.setMessage(e.getMessage());
		
			
		}catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error Occured During User Deletion"+e.getMessage());
			
		}
		return response;
	}

	@Override
	public Response getUserById(String userId) {
		Response response=new  Response();
		try {
			User user=userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new ResourceNotFoundException("User Not found"));
			UserDto userDto=Utils.mapUserEntityToUserDto(user);
			response.setMessage("Successfull");
			response.setStatusCode(200);
			response.setUser(userDto);
			System.out.println(user);
			
		}catch(ResourceNotFoundException e) {
			response.setStatusCode(404);
			response.setMessage(e.getMessage());
		
			
		}catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error Occured During User Registration"+e.getMessage());
			
		}
		return response;
	}



}
