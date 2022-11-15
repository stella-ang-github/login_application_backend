package com.example.demo.service;

import com.example.demo.exception.LoginFailedException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.mdbspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User login(String username, String password) {
        User foundUser = findUserByUsername(username);

        if (foundUser == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        if(foundUser.getPassword() != password) {
            throw new LoginFailedException("Login failed.");
        } else {
            return foundUser;
        }
    }

}
