package com.example.demo.service;

import com.example.demo.exception.LoginFailedException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.mdbspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User login(String username, String password) throws UserNotFoundException, LoginFailedException {
        Optional<User> foundUser = findUserByUsername(username);

        if (foundUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if(foundUser.get().getPassword().equals(password)) {
            return foundUser.get();
        } else {
            throw new LoginFailedException("Login failed.");
        }
    }

}
