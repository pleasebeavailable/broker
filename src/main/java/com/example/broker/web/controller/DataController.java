package com.example.broker.web.controller;

import com.example.broker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getroles")
    public List<String> getRoles() {
        return roleService.findAll();
    }

    @GetMapping("/getuserrole/{id}")
    public List<String> getUserRole(@PathVariable Long id) {
        return roleService.findRolesByUserId(id);
    }
}
