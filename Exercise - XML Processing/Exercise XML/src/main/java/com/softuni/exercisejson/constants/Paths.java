package com.softuni.exercisejson.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USER_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "json","users.json");
    public static final Path CATEGORY_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "json","categories.json");
    public static final Path PRODUCTS_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "json", "products.json");
    public static final Path PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH =
            Path.of("src", "main", "resources", "outputs", "json", "products-in-range.json");
    public static final Path USERS_WITH_SOLD_PRODUCTS_JSON_PATH =
            Path.of("src", "main", "resources", "outputs", "json", "users-sold-products.json");
    public static final Path CATEGORIES_BY_PRODUCTS_JSON_PATH =
            Path.of("src", "main", "resources", "outputs", "json", "categories-by-products.json");
    public static final Path USERS_AND_PRODUCTS_JSON_PATH =
            Path.of("src", "main", "resources", "outputs", "json", "users-and-products.json");

    public static final Path USER_XML_PATH =
            Path.of("src", "main", "resources", "dbContent", "xmls","users.xml");
    public static final Path CATEGORY_XML_PATH =
            Path.of("src", "main", "resources", "dbContent", "xmls","categories.xml");
    public static final Path PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "dbContent", "xmls", "products.xml");
    public static final Path PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH =
            Path.of("src", "main", "resources", "outputs", "xml", "products-in-range.xml");
    public static final Path USERS_WITH_SOLD_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "outputs", "xml", "users-sold-products.xml");
    public static final Path CATEGORIES_BY_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "outputs", "xml", "categories-by-products.xml");
    public static final Path USERS_AND_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "outputs", "xml", "users-and-products.xml");


}
