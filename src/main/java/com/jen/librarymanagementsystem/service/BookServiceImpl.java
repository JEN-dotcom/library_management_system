package com.jen.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jen.librarymanagementsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jen.librarymanagementsystem.entity.Books;
import com.jen.librarymanagementsystem.entity.BorrowedBooks;
import com.jen.librarymanagementsystem.entity.Users;
import com.jen.librarymanagementsystem.error.ObjectNotFoundException;

import com.jen.librarymanagementsystem.repository.BooksRepository;
import com.jen.librarymanagementsystem.repository.BorrowedBooksRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    BorrowedBooksRepository borrowedBooksRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    @Cacheable("allBooks")
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    @CacheEvict(value = "allBooks", allEntries = true)
    public ResponseEntity<Map<String, Object>> addBook(Books Book) {
        booksRepository.save(Book);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Book added successfully");
        response.put("data", Book);
        return ResponseEntity.ok( response);
    }

    @Override
    @Cacheable(value = "getBookById", key = "#id")
    public Books getBookById(long id) {
        return booksRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book not found"));
    }

    @Override
    @Cacheable(value = "getBookByTitle", key = "#title")
    public Books getBookByTitle(String title) {
        return booksRepository.findByTitle(title)
                .orElseThrow(() -> new ObjectNotFoundException("Book not found"));
    }

    @Override
    public List<Books> getBooksByTitle(String title) {
        List<Books> books = booksRepository.findByTitleContaining(title);

        if (books.get(0) == null)
            throw new ObjectNotFoundException("Book(s) not found");
        return books;
    }

    @Override
    @Cacheable(value = "getBookByAuthor", key = "#author")
    public Books getBookByAuthor(String author) {
        return booksRepository.findByAuthor(author)
                .orElseThrow(() -> new ObjectNotFoundException("Book not found"));
    }

    @Override
    public List<Books> getBooksByAuthor(String author) {
        List<Books> books = booksRepository.findByAuthorContaining(author);
        if (books.get(0) == null)
            throw new ObjectNotFoundException("Book(s) not found");
        return books;
    }

    @Override
    @Cacheable(value = "getBookByISBN", key = "#isbn")
    public Books getBookByISBN(long isbn) {
        return booksRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ObjectNotFoundException("Book(s) not found"));
    }

    @Override
    @CacheEvict(value = { "getBookById", "getBookByTitle", "getBookByISBN" }, key = "#isbn")
    public ResponseEntity<String> deleteBook(Integer isbn) {
        Books book = getBookByISBN(isbn);
        booksRepository.deleteById(book.getId());
        return ResponseEntity.ok("Book successfully deleted");
    }

    @Override
    @CacheEvict(value = "allBooks", allEntries = true)
    public ResponseEntity<String> borrowBook(String title, String email) {
        Books book = getBookByTitle(title);
        if (book.isBorrowed()) {
            return new ResponseEntity<String>("Sorry, Book " + title + " has already been borrowed",
                    HttpStatus.CONFLICT);
        }

        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("User not found"));

        book.setUser(user);
        book.setBorrowed(true);
        booksRepository.save(book);

        BorrowedBooks borrowedBooks = new BorrowedBooks();
        borrowedBooks.setUserId(user.getId());
        borrowedBooks.setBookTitle(book.getTitle());
        borrowedBooks.setBookAuthor(book.getAuthor());

        borrowedBooksRepository.save(borrowedBooks);

        return ResponseEntity.ok("Book successfully borrowed");
    }

}
