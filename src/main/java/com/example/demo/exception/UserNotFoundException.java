package com.example.demo.exception;

import com.example.demo.model.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);

    }
}
