package com.example.broker.service;


import com.example.broker.web.dto.UserDTO;

public interface IUserService {

    UserDTO getUser(String username);

    UserDTO createUser(UserDTO user);

}
