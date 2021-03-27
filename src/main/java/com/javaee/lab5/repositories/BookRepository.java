package com.javaee.lab5.repositories;

import com.javaee.lab5.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    boolean existsByIsbn(String isbn);

    List<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findByAuthor(String author);

    List<BookEntity> findByTitle(String title);

    List<BookEntity> findAll();
}
