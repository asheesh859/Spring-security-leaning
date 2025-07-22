package com.spring_security.service;


import com.spring_security.entity.Role;
import com.spring_security.entity.User;

import java.util.List;

public interface UserService {
    User userSave(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username , String rolename);
    User getUser(String username);
    List<User> getUsers();

}
