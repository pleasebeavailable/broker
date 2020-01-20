package com.example.broker.service;

import com.example.broker.repository.model.login.JwtRequest;
import com.example.broker.repository.model.signup.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    ResponseEntity<?> authenticate(JwtRequest authenticationRequest) throws Exception;

    ResponseEntity<?> register(SignUpRequest signUpRequest);
}
