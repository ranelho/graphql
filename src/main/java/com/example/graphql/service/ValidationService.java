package com.example.graphql.service;

import com.example.graphql.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Service
public class ValidationService {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    private static final Pattern ISBN_PATTERN = Pattern.compile(
        "^(?:ISBN[- ]?)?(?:97[89][- ]?)?[\\d][- ]?[\\d][- ]?[\\d][- ]?[\\d][- ]?[\\d][- ]?[\\d][- ]?[\\d][- ]?[\\d][- ]?[\\d][- ]?[\\dX]$"
    );
    
    public void validateAuthor(String firstName, String lastName, String email) {
        if (!StringUtils.hasText(firstName)) {
            throw new ValidationException("firstName", firstName, "First name cannot be empty");
        }
        
        if (firstName.length() < 2) {
            throw new ValidationException("firstName", firstName, "First name must have at least 2 characters");
        }
        
        if (!StringUtils.hasText(lastName)) {
            throw new ValidationException("lastName", lastName, "Last name cannot be empty");
        }
        
        if (lastName.length() < 2) {
            throw new ValidationException("lastName", lastName, "Last name must have at least 2 characters");
        }
        
        if (!StringUtils.hasText(email)) {
            throw new ValidationException("email", email, "Email cannot be empty");
        }
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("email", email, "Invalid email format");
        }
    }
    
    public void validateBook(String title, String isbn, Integer pageCount) {
        if (!StringUtils.hasText(title)) {
            throw new ValidationException("title", title, "Title cannot be empty");
        }
        
        if (title.length() < 3) {
            throw new ValidationException("title", title, "Title must have at least 3 characters");
        }
        
        if (!StringUtils.hasText(isbn)) {
            throw new ValidationException("isbn", isbn, "ISBN cannot be empty");
        }
        
        if (!ISBN_PATTERN.matcher(isbn.replaceAll("[- ]", "")).matches()) {
            throw new ValidationException("isbn", isbn, "Invalid ISBN format");
        }
        
        if (pageCount == null || pageCount <= 0) {
            throw new ValidationException("pageCount", String.valueOf(pageCount), "Page count must be a positive number");
        }
        
        if (pageCount > 10000) {
            throw new ValidationException("pageCount", String.valueOf(pageCount), "Page count cannot exceed 10,000 pages");
        }
    }
    
    public void validateId(Long id, String entityName) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", String.valueOf(id), entityName + " ID must be a positive number");
        }
    }
}