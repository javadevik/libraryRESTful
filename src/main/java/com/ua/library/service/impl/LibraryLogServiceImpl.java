package com.ua.library.service.impl;

import com.ua.library.entities.BookEntity;
import com.ua.library.entities.CustomerEntity;
import com.ua.library.entities.LibraryLogEntity;
import com.ua.library.exceptions.BookIsExcludedException;
import com.ua.library.exceptions.BookIsNotReturnException;
import com.ua.library.exceptions.DateHandlerException;
import com.ua.library.exceptions.LibraryLogNotFoundException;
import com.ua.library.repositories.LibraryLogRepository;
import com.ua.library.service.BookStrippedService;
import com.ua.library.service.CustomerStrippedService;
import com.ua.library.service.LibraryLogService;
import com.ua.library.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LibraryLogServiceImpl implements LibraryLogService {

    private final LibraryLogRepository libraryRepository;
    private final CustomerStrippedService customerService;
    private final BookStrippedService bookService;
    private final DateService dateService;

    @Autowired
    public LibraryLogServiceImpl(LibraryLogRepository libraryRepository,
                                 CustomerStrippedService customerService,
                                 BookStrippedService bookService,
                                 DateService dateService) {
        this.libraryRepository = libraryRepository;
        this.customerService = customerService;
        this.bookService = bookService;
        this.dateService = dateService;
    }

    @Override
    public LibraryLogEntity findById(Long id) throws LibraryLogNotFoundException {
        return libraryRepository.findById(id)
                .orElseThrow(
                        () -> new LibraryLogNotFoundException("Cannot find library log")
                );
    }

    @Override
    public List<LibraryLogEntity> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public LibraryLogEntity save(String dateTaking, String dateShouldReturn,
                                 Long bookId, Long customerId) throws Exception {
        BookEntity book = bookService.findById(bookId);

        if (book.isExcluded())
            throw new BookIsExcludedException("Book have been excluded");

        CustomerEntity customer = customerService.findById(customerId);

        LibraryLogEntity libraryLog = new LibraryLogEntity();

        Timestamp dTaking = dateService.parseToTimestamp(dateTaking);
        Timestamp dShouldReturn = dateService.parseToTimestamp(dateShouldReturn);

        book.setBusy(true);
        libraryLog.setBook(book);

        libraryLog.setCustomer(customer);
        libraryLog.setDateTaking(dTaking);
        libraryLog.setDateShouldReturn(dShouldReturn);

        return libraryRepository.save(libraryLog);
    }

    @Override
    public LibraryLogEntity update(Long libraryLogId, String dateTaking, String dateShouldReturn,
                                   Long bookId, Long customerId) throws Exception {
        LibraryLogEntity logToUpdate = findById(libraryLogId);

        BookEntity book = bookService.findById(bookId);
        CustomerEntity customer = customerService.findById(customerId);

        Timestamp dTaking = dateService.parseToTimestamp(dateTaking);
        Timestamp dShouldReturn = dateService.parseToTimestamp(dateShouldReturn);

        logToUpdate.setBook(book);
        logToUpdate.setCustomer(customer);
        logToUpdate.setDateTaking(dTaking);
        logToUpdate.setDateShouldReturn(dShouldReturn);

        return libraryRepository.save(logToUpdate);
    }

    @Override
    public LibraryLogEntity putDateReturning(Long libraryLogId, String dateReturning)
            throws LibraryLogNotFoundException, DateHandlerException {
        LibraryLogEntity libraryLog = findById(libraryLogId);
        Timestamp dReturning = dateService.parseToTimestamp(dateReturning);

        if (libraryLog.getDateTaking().compareTo(dReturning) > 0)
            throw new DateHandlerException("Cannot return book earlier than it have been took");

        libraryLog.setDateReturning(dReturning);
        libraryLog.getBook().setBusy(false);
        return libraryRepository.save(libraryLog);
    }

    @Override
    public void delete(Long libraryLogId) throws Exception {
        LibraryLogEntity libraryLog = findById(libraryLogId);

        if (libraryLog.getDateReturning() == null)
            throw new BookIsNotReturnException("Book haven't been returned");

        libraryRepository.delete(libraryLog);
    }

}
