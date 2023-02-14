package com.softuni.bookshop.repositories;

import com.softuni.bookshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
