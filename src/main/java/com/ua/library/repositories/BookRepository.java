package com.ua.library.repositories;

import com.ua.library.entities.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findAll();

    BookEntity findByTitle(String title);

    List<BookEntity> findAllByYear(Integer year);

    List<BookEntity> findAllByGenre(String genre);

    List<BookEntity> findAllByPublisher(String publisher);

    @Query(value = "SELECT * FROM books WHERE is_busy = false", nativeQuery = true)
    List<BookEntity> findAllFree();

    List<BookEntity> findAllByNumberOfPage(Integer numberOfPage);
}
