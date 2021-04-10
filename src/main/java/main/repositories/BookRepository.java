package main.repositories;

import main.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    boolean existsByIsbn(String isbn);

    List<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findByAuthor(String author);

    List<BookEntity> findByTitle(String title);
    List<BookEntity> findBookEntityByTitleContains(String partTitle);

    List<BookEntity> findAll();

    boolean existsByTitle(String title);
}
