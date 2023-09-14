package com.jen.librarymanagementsystem.controller;

import com.jen.librarymanagementsystem.entity.Users;

import com.jen.librarymanagementsystem.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users/v1")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> saveUser(@Valid @RequestBody Users user) {
        return userService.addUser(user);
    }

    @GetMapping("/users")
    public List<Users> fetchUsersList() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public Users fetchUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/mail/{email}")
    public Users fetchUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/user/name/{fullName}")
    public Users fetchUserByFullName(@PathVariable("fullName") String fullName) {
        return userService.getUserByEmail(fullName);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("id") Integer id,
            @Valid @RequestBody Users user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }
}
