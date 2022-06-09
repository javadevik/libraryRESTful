package com.ua.library.repositories;

import com.ua.library.entities.LibraryLogEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibraryLogRepository extends CrudRepository<LibraryLogEntity, Long> {
    List<LibraryLogEntity> findAll();
}
