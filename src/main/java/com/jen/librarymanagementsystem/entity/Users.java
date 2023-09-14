package com.jen.librarymanagementsystem.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;




@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_email" })
})
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NotBlank
    @Length(min = 6, max = 15, message = "Name should be at least 6 characters and not more than 15")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Email(message = "Please enter a valid email address")
    @Column(name = "user_email")
    private String email;

    @NotEmpty(message = "Please enter a valid  address")
    private String address;

    @Min(value = 18, message = "Age should be less than 18")
    @Max(value = 70, message = "Age should not be more than 70")
    private int age;
   
}
