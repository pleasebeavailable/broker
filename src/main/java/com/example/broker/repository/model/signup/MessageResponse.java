package com.example.broker.repository.model.signup;

import com.example.broker.web.dto.UserDTO;

public class MessageResponse {

    private String message;
    private UserDTO userDTO;

    public MessageResponse(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
