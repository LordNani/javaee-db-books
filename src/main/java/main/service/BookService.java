package main.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.controller.exceptions.BookIsbnNotUniqueException;
import main.domain.dto.BookDto;
import main.domain.entities.BookEntity;
import main.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public ResponseEntity<String> addBook(BookDto bookDto){
        if(bookRepository.existsByIsbn(bookDto.getIsbn()))
            throw new BookIsbnNotUniqueException();
        BookEntity book = BookEntity.builder()
                .isbn(bookDto.getIsbn())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .build();

        bookRepository.save(book);
        return new ResponseEntity<>("Book added successfully!", HttpStatus.CREATED);
    }

}