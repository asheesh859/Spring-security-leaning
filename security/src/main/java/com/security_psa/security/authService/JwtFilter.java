package com.security_psa.security.authService;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Here you can add your JWT validation logic
       String authHeader =  request.getHeader("Authorization");
         System.out.println("Authorization Header: " + authHeader);
       if(authHeader !=null && authHeader.startsWith("Bearer")){
        String jwtToken = authHeader.substring(7);

        System.out.println(jwtToken);
       }



                }

}
