package com.trainreservation.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainreservation.dto.Response;
import com.trainreservation.serviceint.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Response> getAllUsers(){
		Response response=userService.getAllUser();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	
	@GetMapping("/get-by-id/{userId}")
	public ResponseEntity<Response> getUserById(@PathVariable("userId") String userId){
		Response response=userService.getUserById(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Response> deleteUser(@PathVariable("userId") String userId){
		Response response=userService.deleteUser(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	
	

}
