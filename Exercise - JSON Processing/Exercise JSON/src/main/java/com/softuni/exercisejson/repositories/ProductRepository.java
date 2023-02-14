package com.softuni.exercisejson.repositories;

import com.softuni.exercisejson.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select p.id from Product p where p.price >= :low and p.price <= :high order by p.price")
    Optional<List<Product>> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(String low, String high);
}
