package com.security_psa.security.config;

import java.net.Authenticator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {

	String[] publicEndPoint ={
		"/api/v1/auth/register",
		"/api/v1/auth/login",
		"/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/swagger-resources/**",
        "/webjars/**",
        "/actuator/**", 
        "/eureka/**"
	};
 @Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception{
		http
		.csrf(csfr ->csfr.disable())
		.authorizeHttpRequests(
				 req->{
					req.requestMatchers(publicEndPoint).permitAll()
					.anyRequest().authenticated();
				 }
				);
		return http.build();
		
	}

	@Bean
	public PasswordEncoder getEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}

}

