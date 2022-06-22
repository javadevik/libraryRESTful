package com.ua.library.controllers;

import com.ua.library.entities.BookEntity;
import com.ua.library.exceptions.BookNotFoundException;
import com.ua.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long bookId) {
        try {
            BookEntity bookFound = bookService.findById(bookId);
            return new ResponseEntity<>(bookFound, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> findAll() {
        List<BookEntity> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<BookEntity>> findAllFree() {
        List<BookEntity> books = bookService.findAllFree();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookEntity>> search(@RequestParam(required = false) String title,
                                                   @RequestParam(required = false) String author,
                                                   @RequestParam(required = false) String genre,
                                                   @RequestParam(required = false) Integer year,
                                                   @RequestParam(required = false) String publisher,
                                                   @RequestParam(required = false) Integer numberOfPage) {
        List<BookEntity> books = bookService.search(title, author, genre, year, publisher, numberOfPage);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookEntity> save(@RequestBody BookEntity book) {
        BookEntity bookSaved = bookService.save(book);
        return bookSaved != null
                ? new ResponseEntity<>(bookSaved, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long bookId,
                                    @RequestBody BookEntity book) {
        try {
            BookEntity bookUpdated = bookService.update(bookId, book);
            return new ResponseEntity<>(bookUpdated, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> setAsExcluded(@RequestParam Long bookId,
                                           @RequestParam String reasonExclude) {
        try {
            BookEntity bookExcluded = bookService.setExcludedStatus(bookId, reasonExclude);
            return new ResponseEntity<>(bookExcluded, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long bookId) {
        try {
            bookService.delete(bookId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
