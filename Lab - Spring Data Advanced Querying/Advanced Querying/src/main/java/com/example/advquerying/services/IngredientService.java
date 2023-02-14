package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IngredientService {

    List<Ingredient> selectByName(String name);

    List<Ingredient> selectByNames(List<String> names);


    void deleteByName(String name);

    void updatePrice();
}
