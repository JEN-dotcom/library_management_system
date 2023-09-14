package com.jen.librarymanagementsystem.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;


@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "title", "author"}),
        @UniqueConstraint(columnNames = {"isbn" })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @Column(name = "book_id")
    private long id;

    @NotBlank
    @NotEmpty
    private String title;

    @NotBlank
    @Length(min = 6, max = 15, message = "Name should be at least 6 characters and not more than 15")
    private String author;


    @Min(value = 1400, message = "Invalid publication year")
    private int publicationYear;

    private boolean borrowed = false;

    @NotNull
    @Column(name = "isbn")
    public Integer isbn;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

}
