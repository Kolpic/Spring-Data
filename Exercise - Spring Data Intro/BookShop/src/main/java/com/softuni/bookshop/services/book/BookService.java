package com.softuni.bookshop.services.book;

import com.softuni.bookshop.domain.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBook(List<Book> books);

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findAllByFirstNameAndLastName(String firstName, String lastName);
}
