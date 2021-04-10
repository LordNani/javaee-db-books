package main.repositories;

import main.domain.entities.BookEntity;
import main.domain.entities.PermissionEntity;
import main.domain.entities.UserBookEntity;
import main.domain.entities.UserEntity;
import main.domain.type.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBookEntity, Integer> {
    List<UserBookEntity> findByUser_LoginContains(String login);
    List<UserBookEntity> findByUserAndBook(UserEntity user, BookEntity book);
    boolean existsByUserAndBook(UserEntity user, BookEntity book);
}
