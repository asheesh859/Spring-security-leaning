package com.spring_security.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;
    @ManyToMany(fetch =EAGER)
    private Collection<Role> role = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRole() {
        return role;
    }

    public void setRole(Collection<Role> role) {
        this.role = role;
    }

}
