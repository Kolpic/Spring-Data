package com.softuni.gamestore.services.user;

import com.softuni.gamestore.domain.entities.User;

public interface UserService {

    String registerUser(String[] args) throws IllegalAccessException;

    String loginUser(String[] args);

    String logoutUser();

    User getLoggedInUser();
}
