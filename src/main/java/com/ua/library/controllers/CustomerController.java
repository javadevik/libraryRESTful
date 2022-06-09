package com.ua.library.controllers;

import com.ua.library.entities.CustomerEntity;
import com.ua.library.exceptions.CustomerNotFoundException;
import com.ua.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long customerId) {
        try {
            CustomerEntity customerFound = customerService.findById(customerId);
            return new ResponseEntity<>(customerFound, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> findAll() {
        List<CustomerEntity> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/firstName/lastName")
    public ResponseEntity<List<CustomerEntity>> findAllByFirstNameAndLastName(@RequestParam String firstName,
                                                                              @RequestParam String lastName) {
        List<CustomerEntity> customers = customerService.findAllByFirstNameAndLastName(firstName, lastName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/firstName")
    public ResponseEntity<List<CustomerEntity>> findAllByFirstName(@RequestParam String firstName) {
        List<CustomerEntity> customers = customerService.findAllByFirstName(firstName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/lastName")
    public ResponseEntity<List<CustomerEntity>> findAllByLastName(@RequestParam String lastName) {
        List<CustomerEntity> customers = customerService.findAllByLastName(lastName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/birthDate")
    public ResponseEntity<List<CustomerEntity>> findAllByBirthDate(@RequestParam String birthDate) {
        List<CustomerEntity> customers = customerService.findAllByDateBirth(birthDate);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity<List<CustomerEntity>> findAllByAddress(@RequestParam String address) {
        List<CustomerEntity> customers = customerService.findAllByAddress(address);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/workPlace")
    public ResponseEntity<List<CustomerEntity>> findAllByWorkPlace(@RequestParam String workPlace) {
        List<CustomerEntity> customers = customerService.findAllByWorkPlace(workPlace);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/passport")
    public ResponseEntity<CustomerEntity> findByPassport(@RequestParam String passport) {
        CustomerEntity customer = customerService.findByPassport(passport);
        return customer != null
                ? new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> save(@RequestParam String birthDate,
                                               @RequestBody CustomerEntity customer) {
        CustomerEntity customerSaved = customerService.save(birthDate, customer);
        return customerSaved != null
                ? new ResponseEntity<>(customerSaved, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long customerId,
                                    @RequestParam String birthDate,
                                    @RequestBody CustomerEntity customer) {
        try {
            CustomerEntity customerUpdated = customerService.update(customerId, birthDate, customer);
            return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long customerId) {
        try {
            customerService.delete(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
