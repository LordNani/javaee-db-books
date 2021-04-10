package main.controller.exceptions;

import org.springframework.http.HttpStatus;

public class NotAuthenticatedException extends WebException {

    public NotAuthenticatedException() {
        super("To add books to favorites, user must be authenticated", HttpStatus.UNAUTHORIZED);
    }

}
