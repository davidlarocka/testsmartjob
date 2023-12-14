package com.prueba.bcitest.service;
import java.sql.Date;
import java.time.Instant;
import org.springframework.stereotype.Service;

import com.prueba.bcitest.model.User;

import io.jsonwebtoken.Jwts;

@Service
public class Tokenizar {
	
	public String generatedToken(User user) {
		
	  	String jwtToken = Jwts.builder()
		        .claim("name", user.getName())
		        .claim("password", user.getPassword())
		        .setIssuedAt(Date.from(Instant.now()))
		        //.setExpiration(Date.from(Instant.now().plus(6000l, ChronoUnit.MINUTES)))
		        .compact();
	  	return jwtToken;
		
	}

}
