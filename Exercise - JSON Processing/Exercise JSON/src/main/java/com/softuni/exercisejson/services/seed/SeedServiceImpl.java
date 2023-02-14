package com.softuni.exercisejson.services.seed;

import com.softuni.exercisejson.domain.dtos.categories.CategoryImportDTO;
import com.softuni.exercisejson.domain.dtos.products.ProductImportDTO;
import com.softuni.exercisejson.domain.dtos.users.UserImportDTO;
import com.softuni.exercisejson.domain.entities.Category;
import com.softuni.exercisejson.domain.entities.Product;
import com.softuni.exercisejson.domain.entities.User;
import com.softuni.exercisejson.repositories.CategoryRepository;
import com.softuni.exercisejson.repositories.ProductRepository;
import com.softuni.exercisejson.repositories.UserRepository;
import com.softuni.exercisejson.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.softuni.exercisejson.constants.Paths.*;
import static com.softuni.exercisejson.constants.Utils.*;

@Service
public class SeedServiceImpl implements SeedService {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedUsers() throws IOException {
        if (userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(USER_JSON_PATH.toFile());
            final List<User> users =
                    Arrays.stream(GSON.fromJson(fileReader, UserImportDTO[].class))
                    .map(userImportDTO -> MODEL_MAPPER.map(userImportDTO, User.class))
                            .collect(Collectors.toList());
            userRepository.saveAllAndFlush(users);
            fileReader.close();
        }
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CATEGORY_JSON_PATH.toFile());
            final List<Category> categories =
                    Arrays.stream(GSON.fromJson(fileReader, CategoryImportDTO[].class))
                            .map(categoryImportDTO -> MODEL_MAPPER.map(categoryImportDTO, Category.class))
                            .collect(Collectors.toList());
            categoryRepository.saveAllAndFlush(categories);
            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH.toFile());

            List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ProductImportDTO[].class))
                    .map(productImportDTO -> MODEL_MAPPER.map(productImportDTO, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomCategory)
                    .collect(Collectors.toList());

            productRepository.saveAllAndFlush(products);
            fileReader.close();
        }
    }

    private Product setRandomCategory(Product product) {
        final Random random = new Random();

        final int numberOfCategories = random.nextInt((int) categoryRepository.count());

        Set<Category> categories = new HashSet<>();

        IntStream.range(0, numberOfCategories)
                .forEach(number -> {
                    Category category = categoryRepository.getRandomEntity()
                            .orElseThrow(NoSuchElementException::new);
                    categories.add(category);
                });

        product.setCategories(categories);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(String.valueOf(BigDecimal.valueOf(700L))) > 0) {

            User buyer = userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

            while (buyer.equals(product.getSeller())) {
                userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
            }

            product.setBuyer(buyer);
        }


        return product;
    }

    private Product setRandomSeller(Product product) {
        final User seller = userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

        product.setSeller(seller);

        return product;
    }
}
