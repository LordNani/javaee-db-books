package main.controller.exceptions;

import org.springframework.http.HttpStatus;

public class BookNotExistsException extends WebException {

    public BookNotExistsException() {
        super("Book with given ISBN or Title does not exist", HttpStatus.UNAUTHORIZED);
    }

}
