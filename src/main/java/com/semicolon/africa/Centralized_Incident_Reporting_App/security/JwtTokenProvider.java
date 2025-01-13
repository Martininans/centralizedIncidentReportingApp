package com.semicolon.africa.Centralized_Incident_Reporting_App.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenProvider {
    private final String secretKey = "your-secret-key";  // Secret key for signing JWT
    private final long validityInMilliseconds = 3600000; // 1 hour for token validity

    // Generate a JWT token
    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Extract username from JWT token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()  // For version 0.12.5, use parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)  // Parse the JWT to extract claims
                .getBody();
        return claims.getSubject();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()  // Use parser() here as per the version you're using
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);  // Parse the JWT
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;  // Invalid token
        }
    }
}
