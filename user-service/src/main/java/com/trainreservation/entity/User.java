package com.trainreservation.entity;



import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Email is required")
	@Column(unique=true)
	private String email;
	@NotBlank(message="name is required")
	private String name;
	@NotBlank(message="phonenumber is required")
	private String phonenumber;
	@NotBlank(message="password is required")
	private String password;
	
	private String role;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(role));
	}
	
	@Override
	public String getUsername() {
		
		return email;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	public boolean isAccountNonExpired() {return true;}
	
	public boolean isCredentialNonExpired() {return true;}
	

}