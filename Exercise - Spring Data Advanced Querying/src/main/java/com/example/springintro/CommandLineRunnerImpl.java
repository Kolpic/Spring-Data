package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        final int copies = Integer.parseInt(scanner.nextLine());


    }

    private void removeBooks(int copies) {
        System.out.println(bookService.deleteAllByCopiesLessThan(copies));
    }

    private void increaseBookCopies(String year, int copies) {
        final String changedYear = year.replaceAll(" ", "-");
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        bookService.increaseBookCopies(LocalDate.parse(changedYear,dateTimeFormatter), copies);
    }

    private void ReducedBook(String title) {
        bookService.findFirstByTitle(title);
    }

    private void getCountOfBooksLongerThan(String length) {
        System.out.println(bookService.findCountOfBooksByTitleLongerThan(Integer.parseInt(length)));
    }

    private void bookTitlesSearchByLastNamePrefix(String prefix) {
        bookService.findAllByAuthorLastNameStartingWith(prefix)
                .stream()
                .map(Book::bookTitleAndAuthorFullName)
                .forEach(System.out::println);
    }

    private void booksSearchWithContainingWord(String word) {
        bookService.findAllByTitleContaining(word)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void authorsSearchEndingWith(String suffix) {
        authorService.findAllByFirstNameEndingWith(suffix)
                .forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName()));
    }

    public void allBooksByReleaseDateBefore19920412() {
        bookService.findAllByReleaseDateBefore(LocalDate.of(1992, 4, 12))
                .stream()
                .map(Book::bookTitleEditionTypeAndPriceFormat)
                .forEach(System.out::println);
    }

    public void notReleasedBooksInYear2000() {
        this.bookService.findAllByReleaseDateNot(LocalDate.of(2000,1,1))
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void booksByPriceLowerThan5AndGreaterThan40() {
        bookService.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5L), BigDecimal.valueOf(40L))
                .stream()
                .map(Book::booksTitleAndPriceFormat)
                .forEach(System.out::println);
    }

    public void goldenBookWithLessThan5000Copies() {
        this.bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println );
    }

    private void booksTitlesByAgeRestriction(String ageRestrictionType) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionType.toUpperCase());
        List<Book> allByAgeRestriction = this.bookService.findAllByAgeRestriction(ageRestriction);
        allByAgeRestriction.stream().map(Book::getTitle).forEach(System.out::println);
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
