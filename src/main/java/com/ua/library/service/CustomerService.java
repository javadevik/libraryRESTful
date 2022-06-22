package com.ua.library.service;

import com.ua.library.entities.CustomerEntity;
import com.ua.library.exceptions.CustomerNotFoundException;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    CustomerEntity findById(Long id) throws CustomerNotFoundException;

    List<CustomerEntity> findAll();

    List<CustomerEntity> search(String firstName, String lastName,
                               String birthDate, String address,
                               String workPlace, String passport);

    CustomerEntity save(String birthDate, CustomerEntity customer);

    CustomerEntity update(Long customerId, String birthDate,
                          CustomerEntity customer) throws CustomerNotFoundException;

    void delete(Long customerId) throws Exception;
}
