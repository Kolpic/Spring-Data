package com.softuni.exercisejson.services.products;

import com.softuni.exercisejson.domain.dtos.products.ProductInRangeWithNoBuyerDTO;
import com.softuni.exercisejson.domain.entities.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetweenAndBuyerNullOrderByPrice(BigDecimal low, BigDecimal high) throws IOException;
}
