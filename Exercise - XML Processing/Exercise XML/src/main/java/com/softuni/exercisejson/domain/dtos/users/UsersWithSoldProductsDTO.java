package com.softuni.exercisejson.domain.dtos.users;

import com.softuni.exercisejson.domain.dtos.products.ProductSoldDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersWithSoldProductsDTO {

    private String firstName;
    private String lastName;
    private List<ProductSoldDTO> boughtProducts;
}
