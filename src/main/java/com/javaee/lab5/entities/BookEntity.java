package com.javaee.lab5.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
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
