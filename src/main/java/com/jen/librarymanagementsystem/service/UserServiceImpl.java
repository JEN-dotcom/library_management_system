package com.jen.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jen.librarymanagementsystem.entity.Users;
import com.jen.librarymanagementsystem.error.ObjectNotFoundException;

import com.jen.librarymanagementsystem.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Cacheable("allUsers")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    @CacheEvict(value = "allUsers", allEntries = true)
    public ResponseEntity<Map<String, Object>> addUser(Users user) {
        usersRepository.save(user);

        Map<String, Object> response = new HashMap<>();

        response.put("message", "User added successfully");
        response.put("data", user);
        return ResponseEntity.ok( response);
    }

    @Override
    @Cacheable(value = "getUserById", key = "#id")
    public Users getUserById(Integer id) {
        return usersRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Cacheable(value = "getUserByEmail", key = "#email")
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Override
    @Cacheable(value = "getUserByFullName", key = "#fullName")
    public Users getUserByFullName(String fullName) {
        return usersRepository.findByFullName(fullName)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Override
    @CacheEvict(value = { "getUserById", "getUserByEmail", "getUserByFullName" }, key = "#id")
    public ResponseEntity<Map<String, Object>> updateUser(Integer id, Users userToUpdate) {
        Users user = getUserById(id);

        user.setFullName(userToUpdate.getFullName());
        user.setAddress(userToUpdate.getAddress());
        user.setAge(userToUpdate.getAge());
        user.setEmail(userToUpdate.getEmail());

        usersRepository.save(user);

        Map<String, Object> response = new HashMap<>();

        response.put("message", "User added successfully");
        response.put("data", user);
        return ResponseEntity.ok( response);
    }

    @Override
    @CacheEvict(value = { "getUserById", "getUserByEmail", "getUserByFullName" }, key = "#id")
    public ResponseEntity<String> deleteUser(Integer id) {
        usersRepository.deleteById(id);
        return ResponseEntity.ok("User successfully deleted");
    }

}
