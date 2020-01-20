package com.example.broker.repository.model.signup;

import com.example.broker.repository.model.Role;

import java.util.Collection;

public class SignUpRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Collection<Role> roles;

    public SignUpRequest(String username, String firstName, String lastName, String email, String password, Collection<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
}
