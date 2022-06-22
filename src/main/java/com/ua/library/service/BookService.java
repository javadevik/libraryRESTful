package com.ua.library.service;

import com.ua.library.entities.BookEntity;
import com.ua.library.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {
    BookEntity findById(Long id) throws BookNotFoundException;

    List<BookEntity> findAll();

    List<BookEntity> findAllFree();

    List<BookEntity> search(String title, String author, String genre,
                            Integer year, String publisher, Integer numberOfPage);

    BookEntity save(BookEntity book);

    BookEntity update(Long bookId, BookEntity book) throws BookNotFoundException;

    BookEntity setExcludedStatus(Long bookId, String reasonExcluded) throws Exception;

    void delete(Long bookId) throws Exception;

}
