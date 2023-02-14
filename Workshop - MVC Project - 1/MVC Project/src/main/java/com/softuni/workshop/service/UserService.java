package com.softuni.workshop.service;

import com.softuni.workshop.models.User;
import com.softuni.workshop.models.dtos.LoginDto;
import com.softuni.workshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(LoginDto loginDto) {
        return this.userRepository
                .findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
    }
}
