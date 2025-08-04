package com.example.graphql.service;

import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.repository.AuthorRepository;
import com.example.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    
    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    
    public Author getAuthorForBook(Book book) {
        return book.getAuthor();
    }
    
    public Book createBook(String title, String isbn, int pageCount, Long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author == null) {
            throw new RuntimeException("Author not found with id: " + authorId);
        }
        Book book = new Book(title, isbn, pageCount, author);
        return bookRepository.save(book);
    }
    
    public Author createAuthor(String firstName, String lastName, String email) {
        Author author = new Author(firstName, lastName, email);
        return authorRepository.save(author);
    }
    
    public Book updateBook(Long id, String title, String isbn, Integer pageCount, Long authorId) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            if (title != null) book.setTitle(title);
            if (isbn != null) book.setIsbn(isbn);
            if (pageCount != null) book.setPageCount(pageCount);
            if (authorId != null) {
                Author author = authorRepository.findById(authorId).orElse(null);
                if (author != null) {
                    book.setAuthor(author);
                }
            }
            return bookRepository.save(book);
        }
        return null;
    }
    
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}