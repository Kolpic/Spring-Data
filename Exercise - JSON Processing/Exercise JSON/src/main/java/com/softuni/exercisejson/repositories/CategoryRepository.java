package com.softuni.exercisejson.repositories;

import com.softuni.exercisejson.domain.dtos.categories.CategoryProductSummaryDTO;
import com.softuni.exercisejson.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM `product-shop`.categories order by RAND () LIMIT 1", nativeQuery = true)
    Optional<Category> getRandomEntity();

    @Query(value = "select new com.softuni.exercisejson.domain.dtos.categories.CategoryProductSummaryDTO" +
            "(c.name, count(p.id), avg(p.price), sum(p.price)) " +
            "from Product as p " +
            "join p.categories as c " +
            "group by c.id " +
            "order by count(p.id)")
    Optional<List<CategoryProductSummaryDTO>> getCategorySummary();
}
