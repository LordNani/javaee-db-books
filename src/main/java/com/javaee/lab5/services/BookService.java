package com.javaee.lab5.services;

import com.javaee.lab5.entities.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;


    @Transactional
    public BookEntity createBook(BookEntity bookEntity){
        return  entityManager.merge(bookEntity);
    }

    public boolean existsIsbn(String isbn){
        return !isNull(entityManager.find(BookEntity.class, isbn));
    }

    public BookEntity getBookByIsbn(String isbn){
        return entityManager.find(BookEntity.class, isbn);
    }

    public List<BookEntity> getBooksByAuthor (String author){
        TypedQuery<BookEntity> query = entityManager.createQuery(
                "SELECT book FROM BookEntity book WHERE book.author = '" + author + "'", BookEntity.class);
        return query.getResultList();
    }

    public List<BookEntity> getAllBooks() {
//        List<BookEntity>  list = new ArrayList<>();

        return entityManager.createQuery("SELECT book FROM BookEntity book",BookEntity.class).getResultList();
    }
}
