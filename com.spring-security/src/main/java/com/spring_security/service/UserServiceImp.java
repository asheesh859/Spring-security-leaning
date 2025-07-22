package com.spring_security.service;

import com.spring_security.entity.Role;
import com.spring_security.entity.User;
import com.spring_security.repository.RoleRepository;
import com.spring_security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class UserServiceImp implements UserService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Override
    public User userSave(User user) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String rolename) {

    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }
}
