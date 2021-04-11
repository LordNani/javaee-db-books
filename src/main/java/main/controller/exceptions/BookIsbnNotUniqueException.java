package main.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookIsbnNotUniqueException extends WebException {

    public BookIsbnNotUniqueException() {
        super("Book with given ISBN already is in catalog", HttpStatus.BAD_REQUEST);
    }

}
