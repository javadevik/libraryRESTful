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

import java.util.List;
import java.util.stream.Collectors;

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
    public BookEntity findAllByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<BookEntity> findAllByYear(Integer year) {
        return bookRepository.findAllByYear(year);
    }

    @Override
    public List<BookEntity> findAllByGenre(String genre) {
        return bookRepository.findAllByGenre(genre);
    }

    @Override
    public List<BookEntity> findAllByPublisher(String publisher) {
        return bookRepository.findAllByPublisher(publisher);
    }

    @Override
    public List<BookEntity> findAllByNumberOfPage(Integer numberOfPage) {
        return bookRepository.findAllByNumberOfPage(numberOfPage);
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
