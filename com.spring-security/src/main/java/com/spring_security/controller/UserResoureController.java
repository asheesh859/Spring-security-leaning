package com.spring_security.controller;

import com.spring_security.ApiResponse.ApiResponsePayload;
import com.spring_security.entity.Role;
import com.spring_security.entity.User;
import com.spring_security.repository.UserRepository;
import com.spring_security.service.UserServiceImp;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResoureController {
    private final UserServiceImp userServiceImp;

    @GetMapping("/users")
    public ResponseEntity<ApiResponsePayload<List<User>>> getUsers(){

        System.out.println("request mapping runing");
        List<User> usersResult = userServiceImp.getUsers();
        ApiResponsePayload<List<User>> responsePayload = new ApiResponsePayload<>();

        responsePayload.setStatusCode(200);
        responsePayload.setMessage("users fetch successfully..");
        responsePayload.setData(usersResult);
        ResponseEntity<ApiResponsePayload<List<User>>> responseEntity = new ResponseEntity<>(responsePayload , HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());

        return ResponseEntity.created(uri).body(userServiceImp.userSave(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
            return ResponseEntity.ok().body(userServiceImp.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleTOUserFrom from){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        userServiceImp.addRoleToUser(from.getUsername(),from.getRolename());
        return ResponseEntity.ok(uri).ok().build();
    }


}

@Data
class RoleTOUserFrom{
    private String username;
    private String rolename;
}
