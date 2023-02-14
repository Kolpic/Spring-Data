package com.softuni.exercisejson.domain.dtos.products;

import com.softuni.exercisejson.domain.dtos.categories.CategoryDTO;
import com.softuni.exercisejson.domain.dtos.users.UserDTO;
import com.softuni.exercisejson.domain.entities.Category;
import com.softuni.exercisejson.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private BigDecimal price;
    private UserDTO buyer;
    private UserDTO seller;
    private Set<CategoryDTO> categories;

    public ProductInRangeWithNoBuyerDTO toProductInRangeWithNoBuyerDTO() {
        return new ProductInRangeWithNoBuyerDTO(name, price, seller.getFullName());
    }

}
