package com.softuni.exercisejson;

import com.softuni.exercisejson.services.categories.CategoryService;
import com.softuni.exercisejson.services.products.ProductService;
import com.softuni.exercisejson.services.seed.SeedService;
import com.softuni.exercisejson.services.users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CommandRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    public CommandRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedUsers();

// //       productService.findAllByPriceBetweenAndBuyerNullOrderByPrice(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));

//        userService.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();

// //        categoryService.getCategorySummary();

    }
}
