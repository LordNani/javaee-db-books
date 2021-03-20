package com.javaee.lab5.models;

import com.javaee.lab5.dto.BookDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class BookModel {
    @NotEmpty
    private String isbn;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;


    public static BookModel fromDto(BookDto bookDto) {
        return BookModel.builder()
                .isbn(bookDto.getIsbn())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .build();
    }
}