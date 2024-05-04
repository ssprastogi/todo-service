package com.example.todo;

import org.springframework.http.HttpStatus;

public class TodoException extends Exception {

    public TodoException(String msg, HttpStatus httpStatus) {
        super(msg, new RuntimeException(httpStatus.getReasonPhrase()));
    }
}
