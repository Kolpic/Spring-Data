package com.softuni.bookshop.repositories;

import com.softuni.bookshop.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional <List<Book>> findAllByReleaseDateAfter(LocalDate localDate);

    Optional <List<Book>> findAllByAuthorFirstNameAAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);
}
