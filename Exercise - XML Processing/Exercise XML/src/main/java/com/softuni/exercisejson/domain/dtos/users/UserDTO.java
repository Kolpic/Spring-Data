package com.softuni.exercisejson.domain.dtos.users;

import com.softuni.exercisejson.domain.dtos.products.ProductBasicInfo;
import com.softuni.exercisejson.domain.dtos.products.ProductDTO;
import com.softuni.exercisejson.domain.dtos.products.ProductsSoldWithCountDTO;
import com.softuni.exercisejson.domain.entities.Product;
import com.softuni.exercisejson.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private Set<ProductDTO> sellingProducts;
    private Set<ProductDTO> boughtProducts;
    private Set<UserDTO> friends;

    public String getFullName() {
        return firstName + " " + lastName;
    }

//    public UsersWithProductsWrapperDTO toUsersWithProductsWrapperDTO() {
//        return new UsersWithProductsWrapperDTO();
//    }

//    public UserWithProductsDTO toUserWithProductsDTO() {
//        return new UserWithProductsDTO(firstName, lastName, age, toProductsSoldWithCountDTO());
//    }
//    public List<ProductsSoldWithCountDTO> toProductsSoldWithCountDTO() {
//        return new ProductsSoldWithCountDTO(sellingProducts
//                .stream()
//                .map(this::toProductBasicInfo)
//                .collect(Collectors.toList()));
//    }
//
//    public ProductBasicInfo toProductBasicInfo(ProductDTO productDTO) {
//        return new ProductBasicInfo(productDTO.getName(), productDTO.getPrice());
//    }
}
