package com.jen.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jen.librarymanagementsystem.entity.BorrowedBooks;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {

}
