package br.com.cotinformatica.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenComponent {

	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.expiration}")
	private String jwtExpiration;
	
	
	public String generateToken(final UUID prUserId) {
		Date dataAtual = new Date();
		Date dataExpiration = new Date(dataAtual.getTime() + Long.parseLong(jwtExpiration)); 
		
		return Jwts.builder().setSubject(prUserId.toString())
							 .setNotBefore(dataAtual)
							 .setExpiration(dataExpiration)
							 .signWith(SignatureAlgorithm.HS256, jwtSecret)
							 .compact();
	}
	
}
