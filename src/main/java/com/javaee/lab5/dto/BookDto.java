package com.javaee.lab5.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class BookDto {
    @NotNull
    private String title;

    @NotNull
    private String isbn;

    @NotNull
    private String author;
}
