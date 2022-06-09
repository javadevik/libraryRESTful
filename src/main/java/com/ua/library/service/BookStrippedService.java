package com.ua.library.service;

import com.ua.library.entities.BookEntity;
import com.ua.library.exceptions.BookNotFoundException;

public interface BookStrippedService {
    BookEntity findById(Long id) throws BookNotFoundException;
}
