package com.jen.librarymanagementsystem.repository;

import java.util.Optional;

import com.jen.librarymanagementsystem.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByFullName(String fullName);

}
