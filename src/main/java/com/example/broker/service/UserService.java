package com.example.broker.service;

import com.example.broker.repository.UserRepository;
import com.example.broker.web.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO getUser(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
