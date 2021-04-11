package main.service;


import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import main.domain.entities.BookEntity;
import main.repositories.BookRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RepoInitializer {

    private final BookRepository bookRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        BookEntity book1,book2,book3;
        List<BookEntity> books = new ArrayList<>();
        Faker faker = new Faker();
        for(int i = 0; i < 10; ++i){
            Book book = faker.book();
            books.add(BookEntity.builder().isbn(faker.code().isbn10(true)).title(book.title()).author(book.author()).build());

        }

        bookRepository.saveAll(books);
//        book1 = new BookEntity();
//        book1.setIsbn("1");
//        book1.setTitle("Harry Potter and Cursed Child");
//        book1.setAuthor("J.K.Rowling");
//
//        book2 = new BookEntity();
//        book2.setIsbn("2");
//        book2.setTitle("Name of the Wind");
//        book2.setAuthor("Patrick Rothfuss");
//
//        book3 = new BookEntity();
//        book3.setIsbn("3");
//        book3.setTitle("Bestseller");
//        book3.setAuthor("Bob Ben");

//        bookRepository.save(book1);
//        bookRepository.save(book2);
//        bookRepository.save(book3);
    }
}
