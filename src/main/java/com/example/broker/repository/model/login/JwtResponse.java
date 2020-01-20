package com.example.broker.repository.model.login;

import com.example.broker.repository.model.Role;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String jwttoken, Long id, String username, String email, List<String> roles) {
        this.jwttoken = jwttoken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
