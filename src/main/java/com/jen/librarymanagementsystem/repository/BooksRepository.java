package com.jen.librarymanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jen.librarymanagementsystem.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {

    Optional<Books> findByIsbn(long isbn);

    Optional<Books> findByTitle(String title);

    Optional<Books> findByAuthor(String author);

    List<Books> findByTitleContaining(String title);

    List<Books> findByAuthorContaining(String author);

}
