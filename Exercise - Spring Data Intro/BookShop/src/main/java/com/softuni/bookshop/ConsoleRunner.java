package com.softuni.bookshop;

import com.softuni.bookshop.services.author.AuthorService;
import com.softuni.bookshop.services.book.BookService;
import com.softuni.bookshop.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000,1,1);
    private LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1990,1,1);
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAllData();
        this.getAllBooksAfterAGivenYear();
        this.getAllAuthorsWithBookReleaseDateBefore();
        this.getAllByFirstNameAndLastName("George", "Powell");
    }

    private void getAllBooksAfterAGivenYear() {
        this.bookService
                .findAllByReleaseDateAfter(BOOK_YEAR_AFTER)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void getAllAuthorsWithBookReleaseDateBefore() {
        this.authorService
                .findDistinctByBooksReleaseDateBefore(BOOK_YEAR_BEFORE)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

//    private void getAllOrderByBooks() {
//        this.authorService
//                .findAllOrderByBooks()
//                .forEach(author -> System.out.println(author.getFirstName()
//                        + " " + author.getLastName() + " "
//                + author.getBooks().size()));
//    }

    private void getAllByFirstNameAndLastName(String firstName, String lastName) {
        this.bookService
                .findAllByFirstNameAndLastName(firstName, lastName)
                .forEach(book -> System.out.println(book.getTitle()
                        + " " + book.getReleaseDate() + " "
                        + book.getCopies()));
    }
}
