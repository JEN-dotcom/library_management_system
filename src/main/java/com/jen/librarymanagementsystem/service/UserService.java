package com.jen.librarymanagementsystem.service;

import com.jen.librarymanagementsystem.entity.Users;


import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    public List<Users> getAllUsers();

    public Users getUserById(Integer id);

    public Users getUserByEmail(String email);

    public Users getUserByFullName(String fullName);

    public ResponseEntity<String> deleteUser(Integer id);

    public ResponseEntity<Map<String, Object>> addUser(Users user);

    public ResponseEntity<Map<String, Object>> updateUser(Integer id, Users user);

}
