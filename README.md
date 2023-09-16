# Library Management System

This is the documentation for the Library Management System, a Java-based application designed to manage books, users, and book borrowing operations. The system allows users to post books, retrieve books by ISBN or ID, retrieve all books, delete books, add users, delete users, update user information, retrieve all users, and retrieve users by unique email addresses. Additionally, the system keeps track of borrowed books and caches appropriate data for optimized performance.

## Table of Contents

1. [Introduction](#introduction)
2. [Installation](#installation)
3. [Usage](#usage)
    - [Post Book](#post-book)
    - [Get Book by ISBN](#get-book-by-isbn)
    - [Get Book by ID](#get-book-by-id)
    - [Get All Books](#get-all-books)
    - [Delete Book](#delete-book)
    - [Add User](#add-user)
    - [Delete User](#delete-user)
    - [Update User](#update-user)
    - [Get All Users](#get-all-users)
    - [Get User by Email](#get-user-by-email)
    - [Borrow Book](#borrow-book)
4. [Caching](#caching)
5. [API Documentation](#api-documentation)

---

## Introduction

The Library Management System is a Java-based application that provides a set of functionalities to manage books and users within a library. It allows for book creation, retrieval, deletion, user management, and book borrowing functionality. Books are initially marked as "not borrowed," and user information is stored in a separate user entity database.

## Installation

To use the Library Management System, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/JEN-dotcom/library_management_system


  ## Installation

1. **Build the Application**: Use your preferred build tool (e.g., Maven or Gradle) to build the Java application.

2. **Database Configuration**: Configure the database settings in the application's configuration file.

3. **Run the Application**: Start the application. The API will be accessible at [http://localhost:8080](http://localhost:8080) by default. You can modify the port and other settings in the configuration file.

## Usage

### Post Book

To add a new book to the library, make a POST request to the `/books` endpoint with the book details in the request body. The book will be marked as not borrowed by default.

### Get Book by ISBN

Retrieve a book by its ISBN using a GET request to the `/books/isbn/:isbn` endpoint.

### Get Book by ID

Retrieve a book by its unique ID using a GET request to the `/books/:id` endpoint.

### Get All Books

Retrieve a list of all books in the library by making a GET request to the `/books` endpoint.

### Delete Book

Delete a book from the library using a DELETE request to the `/books/:id` endpoint, where `:id` is the unique ID of the book.

### Add User

Add a new user to the library system by making a POST request to the `/users` endpoint with user details in the request body.

### Delete User

Delete a user from the library system using a DELETE request to the `/users/:id` endpoint, where `:id` is the unique ID of the user.

### Update User

Update user information by making a PUT request to the `/users/:id` endpoint, where `:id` is the unique ID of the user. Provide the updated user details in the request body.

### Get All Users

Retrieve a list of all users in the library system by making a GET request to the `/users` endpoint.

### Get User by Email

Retrieve a user by their unique email address using a GET request to the `/users/email/:email` endpoint.

### Borrow Book

Borrow a book by making a POST request to the `/borrow` endpoint with the user ID and book ID in the request body. This action will mark the book as borrowed and record the user's information in the borrowed books database entity.

## Caching

Each controller in the Library Management System implements appropriate caching mechanisms to improve performance and reduce database queries where applicable. Caching helps optimize the system's response times for frequently accessed data.

## API Documentation

Detailed API documentation and endpoint descriptions can be found in the codebase or generated using API documentation tools like Swagger or Javadoc.
