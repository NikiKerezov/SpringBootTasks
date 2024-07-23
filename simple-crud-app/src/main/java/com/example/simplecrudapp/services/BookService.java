package com.example.simplecrudapp.services;

import com.example.simplecrudapp.entities.Book;
import com.example.simplecrudapp.exceptions.ResourceNotFoundException;
import com.example.simplecrudapp.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public Book saveBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new ResourceNotFoundException("ISBN already exists");
        }
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book updateBook(Long id, Book bookDetails) {
        if (bookDetails.getIsbn() != null && bookRepository.existsByIsbn(bookDetails.getIsbn())) {
            throw new ResourceNotFoundException("ISBN already exists");
        }
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.delete(book);
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingOrAuthorContaining(keyword, keyword);
    }
}