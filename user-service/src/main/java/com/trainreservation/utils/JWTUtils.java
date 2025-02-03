package com.trainreservation.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JWTUtils {
	
	private static final long EXPIRATION_TIME=1000*60*24*7;

	
	private final SecretKey key;
	
	public JWTUtils() {
		String secreteString="2345678765432345676543356785675435675436765432567754325675432567654325653456789876543654365476543234567";
		byte[] keyBytes=Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
		this.key=new SecretKeySpec(keyBytes, "HmacSHA256");
	}
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*24*7))
				.signWith(key).compact();
	}
	
	public String extractUsername(String token) {
		return extractClaims(token,Claims::getSubject);
	}
	private <T> T extractClaims(String token,Function<Claims,T> claimsTFunction){
		return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
	}
	
	public boolean isValidToken(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
		
	}
	
	private boolean isTokenExpired(String token) {
		return extractClaims(token, Claims::getExpiration).before(new Date());
	}

}
