package com.ua.library.service;

import com.ua.library.entities.BookEntity;
import com.ua.library.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {
    BookEntity findById(Long id) throws BookNotFoundException;

    List<BookEntity> findAll();

    List<BookEntity> findAllFree();

    BookEntity findAllByTitle(String title);

    List<BookEntity> findAllByYear(Integer year);

    List<BookEntity> findAllByGenre(String genre);

    List<BookEntity> findAllByPublisher(String publisher);

    List<BookEntity> findAllByNumberOfPage(Integer numberOfPage);

    BookEntity save(BookEntity book);

    BookEntity update(Long bookId, BookEntity book) throws BookNotFoundException;

    BookEntity setExcludedStatus(Long bookId, String reasonExcluded) throws Exception;

    void delete(Long bookId) throws Exception;
}
