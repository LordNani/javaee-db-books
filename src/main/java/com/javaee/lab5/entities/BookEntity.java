package com.javaee.lab5.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "Book")
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String isbn;
    private String title;
    private String author;

}
