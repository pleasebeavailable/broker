package com.example.broker.service;


import com.example.broker.web.dto.UserDTO;

public interface IUserService {

    UserDTO getUser(long id);

    UserDTO createUser(UserDTO user);

}
