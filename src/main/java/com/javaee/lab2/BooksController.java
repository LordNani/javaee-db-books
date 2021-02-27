package com.javaee.lab2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BooksController {
    List<Book> books = new ArrayList<>();

    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model ){
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addBook(@ModelAttribute FormModel formModel, Model model){
        books.add(new Book(formModel.bookTitle, formModel.bookIsbn,formModel.bookAuthor));
        model.addAttribute("books", books);
        return "redirect:/books-list";
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Book {
        private String title;
        private String isbn;
        private String author;
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
