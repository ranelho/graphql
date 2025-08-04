package com.example.graphql.service;

import com.example.graphql.exception.AuthorNotFoundException;
import com.example.graphql.exception.BookNotFoundException;
import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.repository.AuthorRepository;
import com.example.graphql.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    public static final String AUTHOR = "Author";
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ValidationService validationService;
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book getBookById(Long id) {
        validationService.validateId(id, "Book");
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    public Author getAuthorById(Long id) {
        validationService.validateId(id, AUTHOR);
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }
    
    public List<Book> getBooksByAuthor(Long authorId) {
        validationService.validateId(authorId, AUTHOR);
        // Verifica se o autor existe
        authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        return bookRepository.findByAuthorId(authorId);
    }
    
    public Author getAuthorForBook(Book book) {
        return book.getAuthor();
    }
    
    public Book createBook(String title, String isbn, int pageCount, Long authorId) {
        validationService.validateBook(title, isbn, pageCount);
        validationService.validateId(authorId, AUTHOR);
        
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        
        Book book = new Book(title, isbn, pageCount, author);
        return bookRepository.save(book);
    }
    
    public Author createAuthor(String firstName, String lastName, String email) {
        validationService.validateAuthor(firstName, lastName, email);
        
        Author author = new Author(firstName, lastName, email);
        return authorRepository.save(author);
    }
    
    public Book updateBook(Long id, String title, String isbn, Integer pageCount, Long authorId) {
        validationService.validateId(id, "Book");
        
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        
        // Valida apenas os campos que serão atualizados
        if (title != null || isbn != null || pageCount != null) {
            String currentTitle = title != null ? title : book.getTitle();
            String currentIsbn = isbn != null ? isbn : book.getIsbn();
            Integer currentPageCount = pageCount != null ? pageCount : book.getPageCount();
            validationService.validateBook(currentTitle, currentIsbn, currentPageCount);
        }
        
        if (title != null) book.setTitle(title);
        if (isbn != null) book.setIsbn(isbn);
        if (pageCount != null) book.setPageCount(pageCount);
        
        if (authorId != null) {
            validationService.validateId(authorId, AUTHOR);
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new AuthorNotFoundException(authorId));
            book.setAuthor(author);
        }
        
        return bookRepository.save(book);
    }
    
    public boolean deleteBook(Long id) {
        validationService.validateId(id, "Book");
        
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        
        bookRepository.deleteById(id);
        return true;
    }
}