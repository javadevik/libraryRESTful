package com.ua.library.service;

import com.ua.library.entities.LibraryLogEntity;
import com.ua.library.exceptions.DateHandlerException;
import com.ua.library.exceptions.LibraryLogNotFoundException;

import java.util.List;

public interface LibraryLogService {
    LibraryLogEntity findById(Long id) throws LibraryLogNotFoundException;

    List<LibraryLogEntity> findAll();

    LibraryLogEntity save(String dateTaking, String dateShouldReturn,
                          Long bookId, Long customerId) throws Exception;

    LibraryLogEntity update(Long libraryLogId, String dateTaking, String dateShouldReturn,
                            Long bookId, Long customerId) throws Exception;

    LibraryLogEntity putDateReturning(Long libraryLogId, String dateReturning)
            throws LibraryLogNotFoundException, DateHandlerException;

    void delete(Long libraryLogId) throws Exception;
}
