package com.softuni.exercisejson.services.users;

import com.softuni.exercisejson.domain.dtos.users.UsersWithSoldProductsDTO;
import com.softuni.exercisejson.domain.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UsersWithSoldProductsDTO> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException;
}
