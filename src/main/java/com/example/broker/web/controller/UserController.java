package com.example.broker.web.controller;

import com.example.broker.service.UserService;
import com.example.broker.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PostMapping("/authenticate")
    public UserDTO authenticate(@RequestBody UserDTO userDTO) {
        System.out.println("usa");
        return null;
    }
}
