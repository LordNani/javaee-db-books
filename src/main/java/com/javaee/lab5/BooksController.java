package com.javaee.lab5;

import com.javaee.lab5.entities.BookEntity;
import com.javaee.lab5.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BooksController {
    private final BookService bookService;

    @RequestMapping({"/",""})
    public String index(Model model){
        model.addAttribute("books",bookService.getAllBooks() );
        return "index";
    }

    @RequestMapping(value="/book/{isbn}", method = RequestMethod.GET)
    public String getBookByIsbn(@PathVariable String isbn, Model model){
        BookEntity book = bookService.getBookByIsbn(isbn);
        model.addAttribute("bookIsbn",book.getIsbn() );
        model.addAttribute("bookTitle",book.getTitle());
        model.addAttribute("bookAuthor", book.getAuthor());
        return "book";
    }

    @RequestMapping(value="/bookByAuthor/{author}", method = RequestMethod.GET)
    public String getBooksByAuthor(@PathVariable String author, Model model){
        model.addAttribute("author", author);
        model.addAttribute("books",bookService.getBooksByAuthor(author) );
        return "booksList";
    }


    @RequestMapping(value = "/add-book",method = RequestMethod.POST)
    public ResponseEntity<String> addBook(@Valid final BookEntity bookEntity ){
        if(bookService.existsIsbn(bookEntity.getIsbn()))
            return new ResponseEntity<>("This ISBN already exists", HttpStatus.BAD_REQUEST);
        System.out.println("Added book: " + bookService.createBook(bookEntity).toString());
        return new ResponseEntity<>("Book added successfully!", HttpStatus.CREATED);
    }

}
