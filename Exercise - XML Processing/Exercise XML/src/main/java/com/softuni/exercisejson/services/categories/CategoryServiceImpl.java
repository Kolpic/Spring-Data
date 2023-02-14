package com.softuni.exercisejson.services.categories;

import com.softuni.exercisejson.domain.dtos.categories.CategoryProductSummaryDTO;
import com.softuni.exercisejson.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.softuni.exercisejson.constants.Paths.CATEGORIES_BY_PRODUCTS_JSON_PATH;
import static com.softuni.exercisejson.constants.Utils.writeJsonIntoFile;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


//    @Override
//    public List<CategoryProductSummaryDTO> getCategorySummary() throws IOException {
//        final List<CategoryProductSummaryDTO> categoryProductSummaryDTOS =
//                categoryRepository.getCategorySummary().orElseThrow(NoSuchElementException::new);
//
//        writeJsonIntoFile(categoryProductSummaryDTOS, CATEGORIES_BY_PRODUCTS_JSON_PATH);
//
//        return categoryProductSummaryDTOS;
    }

