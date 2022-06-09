package com.ua.library.service;

import com.ua.library.entities.CustomerEntity;
import com.ua.library.exceptions.CustomerNotFoundException;

public interface CustomerStrippedService {
    CustomerEntity findById(Long id) throws CustomerNotFoundException;
}
