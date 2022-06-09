package com.ua.library.service.impl;

import com.ua.library.entities.CustomerEntity;
import com.ua.library.exceptions.BookIsNotReturnException;
import com.ua.library.exceptions.CustomerNotFoundException;
import com.ua.library.repositories.CustomerRepository;
import com.ua.library.service.CustomerService;
import com.ua.library.service.CustomerStrippedService;
import com.ua.library.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService, CustomerStrippedService {

    private final CustomerRepository customerRepository;
    private final DateService dateService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, DateService dateService) {
        this.customerRepository = customerRepository;
        this.dateService = dateService;
    }

    @Override
    public CustomerEntity findById(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Cannot find customer")
                );
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<CustomerEntity> findAllByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<CustomerEntity> findAllByFirstName(String firstName) {
        return customerRepository.findAllByFirstName(firstName);
    }

    @Override
    public List<CustomerEntity> findAllByLastName(String lastName) {
        return customerRepository.findAllByLastName(lastName);
    }

    @Override
    public List<CustomerEntity> findAllByDateBirth(String birthDate) {
        Timestamp bDate = dateService.parseToTimestamp(birthDate);
        return customerRepository.findAllByBirthDate(bDate);
    }

    @Override
    public List<CustomerEntity> findAllByAddress(String address) {
        return customerRepository.findAllByAddress(address);
    }

    @Override
    public List<CustomerEntity> findAllByWorkPlace(String workPlace) {
        return customerRepository.findAllByWorkPlace(workPlace);
    }

    @Override
    public CustomerEntity findByPassport(String passport) {
        return customerRepository.findByPassport(passport);
    }

    @Override
    public CustomerEntity save(String birthDate, CustomerEntity customer) {
        Timestamp date = dateService.parseToTimestamp(birthDate);
        customer.setBirthDate(date);
        return customerRepository.save(customer);
    }

    @Override
    public CustomerEntity update(Long customerId, String birthDate,
                                 CustomerEntity customer) throws CustomerNotFoundException {
        CustomerEntity customerToUpdate = findById(customerId);
        Timestamp date = dateService.parseToTimestamp(birthDate);

        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());

        customerToUpdate.setBirthDate(date);
        customerToUpdate.setAddress(customer.getAddress());

        customerToUpdate.setWorkPlace(customer.getWorkPlace());
        customerToUpdate.setPosition(customer.getPosition());

        customerToUpdate.setPhone(customer.getPhone());
        customerToUpdate.setPassport(customer.getPassport());

        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void delete(Long customerId) throws Exception {
        CustomerEntity customerToDelete = findById(customerId);

        boolean isNotReturning = customerToDelete.getLogs().stream()
                .anyMatch(l -> l.getDateReturning() == null);

        if (isNotReturning)
            throw new BookIsNotReturnException("Book haven't been returned");

        customerRepository.delete(customerToDelete);
    }
}
