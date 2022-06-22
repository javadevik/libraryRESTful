package com.ua.library.service.impl;

import com.ua.library.entities.BookEntity;
import com.ua.library.exceptions.BookIsExcludedException;
import com.ua.library.exceptions.BookIsNotReturnException;
import com.ua.library.exceptions.BookNotFoundException;
import com.ua.library.repositories.BookRepository;
import com.ua.library.service.BookService;
import com.ua.library.service.BookStrippedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService, BookStrippedService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity findById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new BookNotFoundException("Cannot find book")
                );
    }

    @Override
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookEntity> findAllFree() {
        return bookRepository.findAllFree();
    }

    @Override
    public List<BookEntity> search(String title, String author, String genre,
                                   Integer year, String publisher, Integer numberOfPage) {
        if (title != null) {
            return Collections.singletonList(bookRepository.findByTitle(title));
        } else if(author != null) {
            return bookRepository.findAllByAuthor(author);
        } else if (genre != null) {
            return bookRepository.findAllByGenre(genre);
        } else if (year != null) {
            return bookRepository.findAllByYear(year);
        } else if (publisher != null) {
            return bookRepository.findAllByPublisher(publisher);
        } else if (numberOfPage != null) {
            return bookRepository.findAllByNumberOfPage(numberOfPage);
        }

        return null;
    }

    @Override
    public BookEntity save(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public BookEntity update(Long bookId, BookEntity book) throws BookNotFoundException {
        BookEntity bookToUpdate = findById(bookId);
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setGenre(book.getGenre());
        bookToUpdate.setYear(book.getYear());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setNumberOfPage(book.getNumberOfPage());
        return bookRepository.save(bookToUpdate);
    }

    @Override
    public BookEntity setExcludedStatus(Long bookId, String reasonExcluded) throws Exception {
        BookEntity bookToExclude = findById(bookId);

        boolean isNotReturning = bookToExclude.getLogs().stream()
                        .anyMatch(l -> l.getDateReturning() == null);

        if (isNotReturning)
            throw new BookIsNotReturnException("Book haven't been returned",
                    new BookIsExcludedException("Cannot exclude book"));

        bookToExclude.setExcluded(true);
        bookToExclude.setReasonExclude(reasonExcluded);

        return bookRepository.save(bookToExclude);
    }

    @Override
    public void delete(Long bookId) throws Exception {
        BookEntity bookToDelete = findById(bookId);

        boolean isNotReturning = bookToDelete.getLogs().stream()
                .anyMatch(l -> l.getDateReturning() == null);

        if (isNotReturning)
            throw new BookIsNotReturnException("Book haven't been returned");

        bookRepository.delete(bookToDelete);
    }
}
