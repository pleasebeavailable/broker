package com.example.broker.service;

import com.example.broker.web.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Override
    public UserDTO getUser(long id) {
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        return null;
    }
}
