package main.controller;

import main.controller.exceptions.BookIsbnNotUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IOException.class)
    public String handle(final IOException ex) {
        ex.printStackTrace();
        return "error-500";
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Integer> handle(final ArithmeticException ex) {
        return ResponseEntity.badRequest()
                .body(-1);
    }

    @ExceptionHandler(BookIsbnNotUniqueException.class)
    public ResponseEntity<String> handle(final BookIsbnNotUniqueException ex) {
        return new ResponseEntity<>(ex.getMessage(),ex.getHttpStatus());
    }

}