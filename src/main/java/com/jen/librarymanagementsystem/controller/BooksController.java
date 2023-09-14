package com.jen.librarymanagementsystem.controller;

import com.jen.librarymanagementsystem.entity.Books;

import com.jen.librarymanagementsystem.service.BookService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books/v1")
public class BooksController {

    @Autowired
    private BookService BookService;

    @PostMapping("/book")
    public ResponseEntity<Map<String, Object>> saveBook(@Valid @RequestBody Books book) {
        return BookService.addBook(book);
    }

    @GetMapping("/books")
    public List<Books> fetchBooksList() {
        return BookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public Books fetchBookById(@PathVariable long id) {
        return BookService.getBookById(id);
    }

    @GetMapping("/book/author/{author}")
    public Books fetchBookByAuthor(@PathVariable String author) {
        return BookService.getBookByAuthor(author);
    }

    @GetMapping("/books/author/{author}")
    public Books fetchBooksByAuthor(@PathVariable String author) {
        return BookService.getBookByAuthor(author);
    }

    @GetMapping("/book/isbn/{isbn}")
    public Books fetchBookByFullName(@PathVariable long isbn) {
        return BookService.getBookByISBN(isbn);
    }

    @GetMapping("/book/title/{title}")
    public Books fetchBookByTitle(@PathVariable String title) {
        return BookService.getBookByTitle(title);
    }

    @GetMapping("/books/title/{title}")
    public Books fetchBooksByTitle(@PathVariable String title) {
        return BookService.getBookByTitle(title);
    }

    @DeleteMapping("/book/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer isbn) {
        return BookService.deleteBook(isbn);
    }

    @PutMapping("/book/title/{title}/{userEmail}")
    public ResponseEntity<String> borrowBook(
            @PathVariable String title,
            @PathVariable String userEmail) {
        return BookService.borrowBook(title, userEmail);
    }
}
