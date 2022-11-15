package com.example.demo.exception;

import com.example.demo.model.User;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super(message);
    }
}
