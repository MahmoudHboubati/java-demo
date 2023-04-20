package com.example.controllers;

import com.example.domain.Book;
import com.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping("")
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping("")
    public Book createBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public Optional<Book> updateBookById(@PathVariable String id, @RequestBody Book book) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            return Optional.of(repository.save(existingBook));
        } else {
            return Optional.empty();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable String id) {
        repository.deleteById(id);
    }
}
