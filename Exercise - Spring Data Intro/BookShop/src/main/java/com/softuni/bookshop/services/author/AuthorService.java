package com.softuni.bookshop.services.author;

import com.softuni.bookshop.domain.entities.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void seedAuthors(List<Author> authors);

    boolean isDataSeeded();

    Author getRandomAuthor();

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate date);

//    List<Author> findAllOrderByBooks();
}
