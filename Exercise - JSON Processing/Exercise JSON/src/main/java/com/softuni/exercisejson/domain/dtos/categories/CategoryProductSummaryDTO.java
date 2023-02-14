package com.softuni.exercisejson.domain.dtos.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductSummaryDTO {

    private String name;
    private Long productsCount;
    private Double averagePrice;
    private BigDecimal totalRevenue;

}
