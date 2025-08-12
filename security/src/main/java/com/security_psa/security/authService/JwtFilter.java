package com.security_psa.security.authService;

import java.io.IOException;

import com.security_psa.security.SecurityApplication;
import com.security_psa.security.service.CustomUserDetailsService;
import com.security_psa.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Here you can add your JWT validation logic
       String authHeader =  request.getHeader("Authorization");
       if(authHeader !=null && authHeader.startsWith("Bearer")){
        String jwtToken = authHeader.substring(7);
        String username = jwtService.validateTokenAndRetrivingSubject(jwtToken);

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            var userDetails =customUserDetailsService.loadUserByUsername(username);

            var authToken = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
       }
    filterChain.doFilter(request , response);
       }

}
