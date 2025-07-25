package com.spring_security.repository;

import com.spring_security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Role findByName(String name);
}
