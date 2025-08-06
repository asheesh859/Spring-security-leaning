package com.security_psa.security.service;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtService {

    // This class can be used to implement JWT token generation and validation logic
    // For now, it is left empty as per the provided context.
    
    // Example methods could include:
    // - generateToken(User user)
    // - validateToken(String token)
    // - extractUsername(String token)
    // - etc.
    public String generateToken(String username , String role){

       final String SECRET_KEY = "secret_key";
       final Long EXPIRATION_TIME= 86400000L; // 1 day in milliseconds
        
        // Logic to generate JWT token based on username and role
        // This is a placeholder method and should be implemented as per your requirements.
        // Example:
       String jwt = JWT.create()
       .withSubject(username)
       .withClaim("role",role)
       .withIssuedAt(new Date())
       .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
       .sign(Algorithm.HMAC256(SECRET_KEY));

       return jwt;
    }

}
