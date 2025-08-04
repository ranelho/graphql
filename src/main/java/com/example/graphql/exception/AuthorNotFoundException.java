package com.example.graphql.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super(String.format("Author with ID %d not found", id));
    }
    
    public AuthorNotFoundException(String email) {
        super(String.format("Author with email '%s' not found", email));
    }
}