package main.controller.exceptions;

import org.springframework.http.HttpStatus;


public class EmptySearchParamsException extends WebException {

    public EmptySearchParamsException() {
        super("Can't find book if the parameters are empty", HttpStatus.UNAUTHORIZED);
    }

}
