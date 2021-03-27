package com.javaee.lab5.service;

import com.javaee.lab5.entities.BookEntity;
import com.javaee.lab5.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepoInitializer {

    private final BookRepository bookRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        BookEntity book1,book2,book3;

        book1 = new BookEntity();
        book1.setIsbn("1");
        book1.setTitle("Harry Potter and Cursed Child");
        book1.setAuthor("J.K.Rowling");

        book2 = new BookEntity();
        book2.setIsbn("2");
        book2.setTitle("Name of the Wind");
        book2.setAuthor("Patrick Rothfuss");

        book3 = new BookEntity();
        book3.setIsbn("3");
        book3.setTitle("Bestseller");
        book3.setAuthor("Bob Ben");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }
}
