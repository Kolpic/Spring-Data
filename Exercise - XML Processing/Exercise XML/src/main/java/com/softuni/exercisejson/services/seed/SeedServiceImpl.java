package com.softuni.exercisejson.services.seed;

import com.softuni.exercisejson.domain.dtos.categories.wrappers.CategoriesImportWrapperDTO;
import com.softuni.exercisejson.domain.dtos.products.wrappers.ProductsImportWrapperDTO;
import com.softuni.exercisejson.domain.dtos.users.wrappers.UsersImportWrapperDTO;
import com.softuni.exercisejson.domain.entities.Category;
import com.softuni.exercisejson.domain.entities.Product;
import com.softuni.exercisejson.domain.entities.User;
import com.softuni.exercisejson.repositories.CategoryRepository;
import com.softuni.exercisejson.repositories.ProductRepository;
import com.softuni.exercisejson.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
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
    public void seedUsers() throws IOException, JAXBException {
        if (userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(USER_XML_PATH.toFile());

//            final List<User> users =
//                    Arrays.stream(GSON.fromJson(fileReader, UserImportDTO[].class))
//                    .map(userImportDTO -> MODEL_MAPPER.map(userImportDTO, User.class))
//                            .collect(Collectors.toList());

            final JAXBContext context = JAXBContext.newInstance(UsersImportWrapperDTO.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();

            final UsersImportWrapperDTO usersDto = (UsersImportWrapperDTO) unmarshaller.unmarshal(fileReader);

            final List<User> users = usersDto.getUsers()
                            .stream()
                                    .map(userDTO -> MODEL_MAPPER.map(userDTO, User.class))
                                            .toList();

            userRepository.saveAllAndFlush(users);

            fileReader.close();

        }
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        if (categoryRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CATEGORY_XML_PATH.toFile());

//            final List<Category> categories =
//                    Arrays.stream(GSON.fromJson(fileReader, CategoryImportDTO[].class))
//                            .map(categoryImportDTO -> MODEL_MAPPER.map(categoryImportDTO, Category.class))
//                            .collect(Collectors.toList());

            final JAXBContext context = JAXBContext.newInstance(CategoriesImportWrapperDTO.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();

            final CategoriesImportWrapperDTO categoriesDto = (CategoriesImportWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<Category> categories = categoriesDto.getCategories()
                    .stream()
                    .map(categoryImportDTO -> MODEL_MAPPER.map(categoryImportDTO, Category.class))
                    .toList();

            categoryRepository.saveAllAndFlush(categories);

            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        if (productRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PRODUCTS_XML_PATH.toFile());

//            List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ProductImportDTO[].class))
//                    .map(productImportDTO -> MODEL_MAPPER.map(productImportDTO, Product.class))
//                    .map(this::setRandomSeller)
//                    .map(this::setRandomBuyer)
//                    .map(this::setRandomCategory)
//                    .collect(Collectors.toList());

            final JAXBContext context = JAXBContext.newInstance(ProductsImportWrapperDTO.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();

            ProductsImportWrapperDTO productsDto = (ProductsImportWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<Product> products = productsDto.getProducts()
                    .stream()
                    .map(productImportDTO -> MODEL_MAPPER.map(productImportDTO, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomCategory)
                    .toList();

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
        if (product.getPrice().compareTo(BigDecimal.valueOf(700L)) > 0) {

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
