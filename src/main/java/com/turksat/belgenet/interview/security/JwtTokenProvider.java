package com.turksat.belgenet.interview.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	@Value("${belgenet.app.secret}")
	private String APP_SECRET;

	@Value("${belgenet.app.expires.in}")
	private long EXPIRES_IN;

	public String generateJwtToken(Authentication auth) {
		JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET)
				.compact();
	}

	public Long getUserIdFromJwt(String token) {
		Claims claims = getClaimsJwsInstance(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public boolean validate(String token) {
		try {
			getClaimsJwsInstance(token);
			return !isTokenExpired(token);
		} catch (SignatureException e) {
			return false;
		} catch (MalformedJwtException e) {
			return false;
		} catch (ExpiredJwtException e) {
			return false;
		} catch (UnsupportedJwtException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	private boolean isTokenExpired(String token) {
		Date expireDate = getClaimsJwsInstance(token).getBody().getExpiration();
		return expireDate.before(new Date());
	}

	private Jws<Claims> getClaimsJwsInstance(String token) {
		return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
	}
}
