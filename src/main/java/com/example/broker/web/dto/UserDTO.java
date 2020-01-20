package com.example.broker.web.dto;

import com.example.broker.repository.model.login.JwtResponse;
import com.example.broker.repository.model.Role;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Role> roles = new ArrayList<>();



    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private JwtResponse jwtResponse;

    public JwtResponse getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
