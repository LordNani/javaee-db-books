package com.javaee.lab5;

import com.javaee.lab5.dto.BookDto;
import com.javaee.lab5.services.BookService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BooksController {
    List<Book> books = new ArrayList<>();
    BookService bookService;

    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model ){
        model.addAttribute("books", books);
        return "index";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/add-book")
    public void addBook(@Valid @RequestBody BookDto bookDto ){
        bookService.createBook(bookDto);
    }



    @Getter
    @Setter
    @AllArgsConstructor
    class FormModel{
        private String bookTitle;
        private String bookIsbn;
        private String bookAuthor;
    }
}
