package com.spring_security;

import com.spring_security.entity.Role;
import com.spring_security.entity.User;
import com.spring_security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.userSave(new User(null,"john" , "john3635" , "12345" , new ArrayList<>()));
			userService.userSave(new User(null,"mike andrew" , "mike" , "12345" , new ArrayList<>()));
			userService.userSave(new User(null,"Alice thomas" , "alice" , "12345" , new ArrayList<>()));


			userService.addRoleToUser("john3635" ,"ROLE_USER");
			userService.addRoleToUser("mike" ,"ROLE_MANAGER");
			userService.addRoleToUser("alice" ,"ROLE_ADMIN");


		};
	}
}
