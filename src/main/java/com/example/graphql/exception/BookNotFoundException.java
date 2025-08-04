package com.example.graphql.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(String.format("Book with ID %d not found", id));
    }
    
    public BookNotFoundException(String title) {
        super(String.format("Book with title '%s' not found", title));
    }
}