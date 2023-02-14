package com.softuni.exercisejson.services.users;

import com.softuni.exercisejson.domain.dtos.users.UsersWithSoldProductsDTO;
import com.softuni.exercisejson.repositories.UserRepository;
import com.softuni.exercisejson.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.softuni.exercisejson.constants.Paths.USERS_WITH_SOLD_PRODUCTS_JSON_PATH;
import static com.softuni.exercisejson.constants.Utils.MODEL_MAPPER;
import static com.softuni.exercisejson.constants.Utils.writeJsonIntoFile;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UsersWithSoldProductsDTO> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException {
        List<UsersWithSoldProductsDTO> usersWithSoldProductsDTOList = userRepository
                .findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UsersWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        writeJsonIntoFile(usersWithSoldProductsDTOList, USERS_WITH_SOLD_PRODUCTS_JSON_PATH);

        return usersWithSoldProductsDTOList;
    }
}
