package com.example.broker.web.controller;

import com.example.broker.repository.model.login.JwtRequest;
import com.example.broker.repository.model.signup.SignUpRequest;
import com.example.broker.service.AuthService;
import com.example.broker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return authService.authenticate(authenticationRequest);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.register(signUpRequest);
    }
}
