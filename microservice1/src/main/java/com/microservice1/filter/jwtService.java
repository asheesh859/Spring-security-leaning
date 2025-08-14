package com.microservice1.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Date;

public class jwtService {

    private static final String SECRET_KEY = "test";
    private static final long EXPIRATION_TIME= 86400000;

    public String generateToken(String username , String role){
        String jwtToken = JWT.create().withSubject(username)
                .withClaim("role" ,role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));

        return jwtToken;
    }

    public String validateTokenAndRetrieveSubject(String token){
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build().verify(token).getSubject();
    }
}
