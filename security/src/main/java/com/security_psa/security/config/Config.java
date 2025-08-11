package com.security_psa.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;	
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security_psa.security.authService.JwtFilter;
import com.security_psa.security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class Config {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtFilter filter;

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
		//.csrf(csfr ->csfr.disable())
		.authorizeHttpRequests(
				 req->{
					req.requestMatchers(publicEndPoint)
							.permitAll()
							.requestMatchers("/api/v1/admin/welcome").hasRole("ADMIN")
							.anyRequest()
							.authenticated();
				 }).authenticationProvider(authProvider())
				.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
		return http.csrf().disable().build();
		
	}

	@Bean
	public PasswordEncoder getEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authProvider(){
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(customUserDetailsService);
		authProvider.setPasswordEncoder(getEncoder());
		return authProvider;
		
	}



}

