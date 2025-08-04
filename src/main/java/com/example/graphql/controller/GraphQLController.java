package com.example.graphql.controller;

import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class GraphQLController {
    
    @Autowired
    private BookService bookService;
    
    // === QUERIES ===
    
    @QueryMapping
    public List<Book> allBooks() {
        return bookService.getAllBooks();
    }
    
    @QueryMapping
    public Book bookById(@Argument String id) {
        return bookService.getBookById(id);
    }
    
    @QueryMapping
    public List<Author> allAuthors() {
        return bookService.getAllAuthors();
    }
    
    @QueryMapping
    public Author authorById(@Argument String id) {
        return bookService.getAuthorById(id);
    }
    
    @QueryMapping
    public List<Book> booksByAuthor(@Argument String authorId) {
        return bookService.getBooksByAuthor(authorId);
    }
    
    // === MUTATIONS ===
    
    @MutationMapping
    public Book createBook(@Argument Map<String, Object> input) {
        String title = (String) input.get("title");
        String isbn = (String) input.get("isbn");
        Integer pageCount = (Integer) input.get("pageCount");
        String authorId = (String) input.get("authorId");
        
        return bookService.createBook(title, isbn, pageCount, authorId);
    }
    
    @MutationMapping
    public Author createAuthor(@Argument Map<String, Object> input) {
        String firstName = (String) input.get("firstName");
        String lastName = (String) input.get("lastName");
        String email = (String) input.get("email");
        
        return bookService.createAuthor(firstName, lastName, email);
    }
    
    @MutationMapping
    public Book updateBook(@Argument String id, @Argument Map<String, Object> input) {
        String title = (String) input.get("title");
        String isbn = (String) input.get("isbn");
        Integer pageCount = (Integer) input.get("pageCount");
        String authorId = (String) input.get("authorId");
        
        return bookService.updateBook(id, title, isbn, pageCount, authorId);
    }
    
    @MutationMapping
    public Boolean deleteBook(@Argument String id) {
        return bookService.deleteBook(id);
    }
    
    // === FIELD RESOLVERS ===
    
    @SchemaMapping
    public Author author(Book book) {
        return bookService.getAuthorById(book.getAuthorId());
    }
    
    @SchemaMapping
    public List<Book> books(Author author) {
        return bookService.getBooksByAuthor(author.getId());
    }
}