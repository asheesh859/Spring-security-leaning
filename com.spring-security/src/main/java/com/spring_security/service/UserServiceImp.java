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

        User userResult = userRepository.save(user);
        return userResult;
    }

    @Override
    public Role saveRole(Role role) {
        Role roleResult = roleRepository.save(role);
        return roleResult;
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        boolean add = user.getRole().add(role);
    }

    @Override
    public User getUser(String username) {
        User getUser = userRepository.findByUsername(username);
        return getUser;
    }

    @Override
    public List<User> getUsers() {
        List<User> allUser = userRepository.findAll();
        return allUser;
    }
}
