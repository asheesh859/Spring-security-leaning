package com.security_psa.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security_psa.security.ApiResponse.ApiResponse;
import com.security_psa.security.payload.LoginPayload;
import com.security_psa.security.payload.UserPayload;
import com.security_psa.security.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserPayload userPayload) {

        ApiResponse<String> response = authService.registerUser(userPayload);
        ResponseEntity<ApiResponse<String>> responseEntity = new ResponseEntity<>(response , HttpStatus.CREATED);
        return responseEntity;
        
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestBody LoginPayload loginPayload) {

        ApiResponse<String> response = new ApiResponse<>();
        UsernamePasswordAuthenticationToken token = 
				 new UsernamePasswordAuthenticationToken(loginPayload.getUsername(), loginPayload.getPassword());
                 try{

                    Authentication authetication = authManager.authenticate(token);
                    if(authetication.isAuthenticated()){
                        response.setMessage("Login successful");
                       response.setStatusCode(200);
                       response.setData("user logged in successfully");
                       ResponseEntity<ApiResponse<String>> responseEntity= new ResponseEntity<>(response,HttpStatus.OK);
                       return responseEntity;
                    }
                 }catch(Exception e){
                    e.printStackTrace();
                 }

                 response.setMessage("Failed to login");
                 response.setStatusCode(401);
                 response.setData("Invalid username or password");
                 ResponseEntity<ApiResponse<String>> responseEntity = new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                 return responseEntity;
    }
   }
   
    

