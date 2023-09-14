package com.jen.librarymanagementsystem.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.jen.librarymanagementsystem.entity.Books;


public interface BookService {

    public List<Books> getAllBooks();

    public Books getBookById(long id);

    public Books getBookByISBN(long isbn);

    public Books getBookByTitle(String title);

    public Books getBookByAuthor(String author);

    public List<Books> getBooksByTitle(String title);

    public List<Books> getBooksByAuthor(String author);

    public ResponseEntity<String> deleteBook(Integer isbn);

    public ResponseEntity<Map<String, Object>> addBook(Books book);

    public ResponseEntity<String> borrowBook(String title, String email);

}
