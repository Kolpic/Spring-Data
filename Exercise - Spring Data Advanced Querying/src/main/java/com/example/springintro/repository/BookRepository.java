package com.example.springintro.repository;

import com.example.springintro.model.dto.BookInformation;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    Optional<List<Book>> findAllByAgeRestriction(AgeRestriction ageRestriction);

    Optional<List<Book>> findAllByEditionTypeAndCopiesLessThan(EditionType type, Integer copiesNumber);

    Optional<List<Book>> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high);

    Optional<List<Book>> findAllByReleaseDateNot(LocalDate date);

    Optional<List<Book>> findAllByTitleContaining(String word);

    Optional<List<Book>> findAllByAuthorLastNameStartingWith(String prefix);

    @Query("select count(b.id) from Book AS b where length(b.title) > :length")
    Optional<Integer> findCountOfBooksByTitleLongerThan(Integer length);

    @Query("select new com.example.springintro.model.dto.BookInformation" +
            "(b.title, b.editionType, b.ageRestriction, b.price) from Book b where b.title = :title")
    Optional<BookInformation> findFirstByTitle(String title);

    @Modifying
    @Transactional
    @Query("update Book b set b.copies = b.copies + :copies where b.releaseDate > :date")
    int increaseBookCopies(LocalDate date, int copies);

    @Transactional
    int deleteAllByCopiesLessThan(Integer copies);

//    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);
}
