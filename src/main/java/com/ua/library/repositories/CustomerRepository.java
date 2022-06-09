package com.ua.library.repositories;

import com.ua.library.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    List<CustomerEntity> findAll();

    List<CustomerEntity> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<CustomerEntity> findAllByFirstName(String firstName);

    List<CustomerEntity> findAllByLastName(String lastName);

    List<CustomerEntity> findAllByBirthDate(Timestamp birthDate);

    List<CustomerEntity> findAllByAddress(String address);

    List<CustomerEntity> findAllByWorkPlace(String workPlace);

    CustomerEntity findByPassport(String passport);
}
