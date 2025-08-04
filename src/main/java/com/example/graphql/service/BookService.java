package com.example.graphql.service;

import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Author> authors = new HashMap<>();
    
    public BookService() {
        initializeData();
    }
    
    private void initializeData() {
        // Criar alguns autores
        Author author1 = new Author("1", "Robert", "Martin", "robert@cleancode.com");
        Author author2 = new Author("2", "Martin", "Fowler", "martin@refactoring.com");
        Author author3 = new Author("3", "Joshua", "Bloch", "joshua@effective.java");
        
        authors.put("1", author1);
        authors.put("2", author2);
        authors.put("3", author3);
        
        // Criar alguns livros
        Book book1 = new Book("1", "Clean Code", "978-0132350884", 464, "1");
        Book book2 = new Book("2", "Clean Architecture", "978-0134494166", 432, "1");
        Book book3 = new Book("3", "Refactoring", "978-0201485677", 431, "2");
        Book book4 = new Book("4", "Effective Java", "978-0134685991", 416, "3");
        
        books.put("1", book1);
        books.put("2", book2);
        books.put("3", book3);
        books.put("4", book4);
    }
    
    // Métodos para Books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    
    public Book getBookById(String id) {
        return books.get(id);
    }
    
    public List<Book> getBooksByAuthor(String authorId) {
        return books.values().stream()
                .filter(book -> book.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }
    
    public Book createBook(String title, String isbn, Integer pageCount, String authorId) {
        String id = String.valueOf(books.size() + 1);
        Book book = new Book(id, title, isbn, pageCount != null ? pageCount : 0, authorId);
        books.put(id, book);
        return book;
    }
    
    public Book updateBook(String id, String title, String isbn, Integer pageCount, String authorId) {
        Book book = books.get(id);
        if (book != null) {
            book.setTitle(title);
            book.setIsbn(isbn);
            book.setPageCount(pageCount != null ? pageCount : 0);
            book.setAuthorId(authorId);
        }
        return book;
    }
    
    public boolean deleteBook(String id) {
        return books.remove(id) != null;
    }
    
    // Métodos para Authors
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }
    
    public Author getAuthorById(String id) {
        return authors.get(id);
    }
    
    public Author createAuthor(String firstName, String lastName, String email) {
        String id = String.valueOf(authors.size() + 1);
        Author author = new Author(id, firstName, lastName, email);
        authors.put(id, author);
        return author;
    }
}