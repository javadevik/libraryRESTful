package com.ua.library.controllers;

import com.ua.library.entities.CustomerEntity;
import com.ua.library.exceptions.CustomerNotFoundException;
import com.ua.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/search")
    public ResponseEntity<List<CustomerEntity>> search(@RequestParam(required = false) String firstName,
                                                       @RequestParam(required = false) String lastName,
                                                       @RequestParam(required = false) String birthDate,
                                                       @RequestParam(required = false) String address,
                                                       @RequestParam(required = false) String workPlace,
                                                       @RequestParam(required = false) String passport) {
        List<CustomerEntity> customers = customerService.search(firstName, lastName,
                birthDate, address, workPlace, passport);
        return new ResponseEntity<>(customers, HttpStatus.OK);
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
