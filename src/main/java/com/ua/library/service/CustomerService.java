package com.ua.library.service;

import com.ua.library.entities.CustomerEntity;
import com.ua.library.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerEntity findById(Long id) throws CustomerNotFoundException;

    List<CustomerEntity> findAll();

    List<CustomerEntity> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<CustomerEntity> findAllByFirstName(String firstName);

    List<CustomerEntity> findAllByLastName(String lastName);

    List<CustomerEntity> findAllByDateBirth(String birthDate);

    List<CustomerEntity> findAllByAddress(String address);

    List<CustomerEntity> findAllByWorkPlace(String workPlace);

    CustomerEntity findByPassport(String passport);

    CustomerEntity save(String birthDate, CustomerEntity customer);

    CustomerEntity update(Long customerId, String birthDate,
                          CustomerEntity customer) throws CustomerNotFoundException;

    void delete(Long customerId) throws Exception;
}
