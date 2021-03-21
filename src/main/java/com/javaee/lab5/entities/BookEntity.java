package com.javaee.lab5.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name="BookEntity")
public class BookEntity {

    @Id
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;

//
//    public static BookEntity fromDto(BookDto bookDto) {
//        return BookEntity.builder()
//                .isbn(bookDto.getIsbn())
//                .title(bookDto.getTitle())
//                .author(bookDto.getAuthor())
//                .build();
//    }
}
