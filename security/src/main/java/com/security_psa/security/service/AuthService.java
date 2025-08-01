package com.security_psa.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security_psa.security.ApiResponse.ApiResponse;
import com.security_psa.security.entity.User;
import com.security_psa.security.payload.UserPayload;
import com.security_psa.security.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse<String> registerUser(UserPayload userPayload){

        if(userRepository.existsByUsername(userPayload.getUserName())){
            ApiResponse<String> response = new ApiResponse<>();
            response.setMessage("Username already exists");
            response.setStatusCode(400);
            response.setData("Registration failed with exist username");
            return response;
            
        }

        if(userRepository.existsByEmail(null)){
            ApiResponse<String> response = new ApiResponse<>();
            response.setMessage("Email already exists");
            response.setStatusCode(400);
            response.setData("Registration failed with exist email");
            return response;

        }
        User user = new User();
        BeanUtils.cpyProperties(userPayload, user);
        user.setPassword(passwordEncoder.encode(userPayload.getpassword()));
        userRepository.save(user);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("User registerd successfully");
        response.setStatusCode(200);
        response.setData("User registered successfully with username:"+user.getUserName());
        return response;
    }

}
