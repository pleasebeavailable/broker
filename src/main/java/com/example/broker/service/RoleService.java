package com.example.broker.service;

import com.example.broker.repository.RoleRepository;
import com.example.broker.repository.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<String> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<String> rolesString = roles.stream().map(role -> role.getName()).collect(Collectors.toList());

        return rolesString;
    }

    @Override
    public List<String> findRolesByUserId(Long id) {
        return roleRepository.findRolesByUserId(id);
    }
}
