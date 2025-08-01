package com.security_psa.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security_psa.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
