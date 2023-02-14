package com.softuni.bookshop.repositories;

import com.softuni.bookshop.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorBy(Long number);

    Optional<List<Author>> findDistinctByBooksReleaseDateBefore(LocalDate date);

//    Optional<List<Author>> findAllOrderByBooks();
}
