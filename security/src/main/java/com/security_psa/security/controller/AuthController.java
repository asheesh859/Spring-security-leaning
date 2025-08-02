package com.security_psa.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security_psa.security.ApiResponse.ApiResponse;
import com.security_psa.security.payload.UserPayload;
import com.security_psa.security.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserPayload userPayload) {

        ApiResponse<String> response = authService.registerUser(userPayload);
        ResponseEntity<ApiResponse<String>> responseEntity = new ResponseEntity<>(response , HttpStatus.CREATED);
        return responseEntity;
        
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserPayload userPayload) {
        // Logic to authenticate the user
        return "User logged in successfully!";
    }
   }
   
    

