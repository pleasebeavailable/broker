package com.example.broker.service;

import com.example.broker.repository.model.Role;

import java.util.List;

public interface IRoleService {

    List<String> findAll();

    List<String> findRolesByUserId(Long id);


}
