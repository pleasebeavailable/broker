package com.example.broker.web.controller;

import com.example.broker.repository.model.JwtRequest;
import com.example.broker.repository.model.JwtResponse;
import com.example.broker.service.AuthService;
import com.example.broker.service.UserService;
import com.example.broker.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return authService.authenticate(authenticationRequest);
    }
}
