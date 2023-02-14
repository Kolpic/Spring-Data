package com.softuni.exercisejson.services.products;

import com.softuni.exercisejson.domain.dtos.products.ProductDTO;
import com.softuni.exercisejson.domain.dtos.products.ProductInRangeWithNoBuyerDTO;
import com.softuni.exercisejson.repositories.ProductRepository;
import com.softuni.exercisejson.services.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.softuni.exercisejson.constants.Paths.*;
import static com.softuni.exercisejson.constants.Utils.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
//    @Transactional
    public List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetweenAndBuyerNullOrderByPrice
            (BigDecimal low, BigDecimal high) throws IOException {
        List<ProductInRangeWithNoBuyerDTO> products =
                productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(String.valueOf(low), String.valueOf(high))
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> MODEL_MAPPER.map(product, ProductDTO.class))
                .map(ProductDTO::toProductInRangeWithNoBuyerDTO)
                .collect(Collectors.toList());

        writeJsonIntoFile(products, PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH);

        return products;
    }
}
