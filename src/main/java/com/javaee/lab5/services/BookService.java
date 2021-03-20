package com.javaee.lab5.services;

import com.javaee.lab5.dto.BookDto;
import com.javaee.lab5.models.BookModel;
import org.springframework.stereotype.Service;



@Service
public class BookService {

    public void createBook(BookDto bookDto){
        BookModel book = BookModel.fromDto(bookDto);
        
    }
}
