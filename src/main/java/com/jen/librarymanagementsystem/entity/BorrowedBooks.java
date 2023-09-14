package com.jen.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Setter


public class BorrowedBooks {
    @Id
    private String id;

    private long userId;
    private String bookTitle;
    private String bookAuthor;

    public BorrowedBooks() {
        this.id = UUID.randomUUID().toString();
    }
}
