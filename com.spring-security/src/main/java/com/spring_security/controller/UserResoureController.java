package com.spring_security.controller;

import com.spring_security.ApiResponse.ApiResponsePayload;
import com.spring_security.entity.User;
import com.spring_security.repository.UserRepository;
import com.spring_security.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserResoureController {
    private final UserServiceImp userServiceImp;

    @GetMapping("/users")
    public ResponseEntity<ApiResponsePayload<List<User>>> getUsers(){
        List<User> usersResult = userServiceImp.getUsers();
        ApiResponsePayload<List<User>> responsePayload = new ApiResponsePayload<>();

        responsePayload.setStatusCode(200);
        responsePayload.setMessage("users fetch successfully..");
        responsePayload.setData(usersResult);
        ResponseEntity<ApiResponsePayload<List<User>>> responseEntity = new ResponseEntity<>(responsePayload , HttpStatus.OK);


        return responseEntity;
    }
}
