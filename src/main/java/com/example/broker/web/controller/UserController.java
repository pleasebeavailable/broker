package com.example.broker.web.controller;

import com.example.broker.service.UserService;
import com.example.broker.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public UserDTO getUser(@PathVariable String username) {
        System.out.println(username);
        return userService.getUser(username);
    }
}
