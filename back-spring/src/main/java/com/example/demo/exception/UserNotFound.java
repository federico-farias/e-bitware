package com.example.demo.exception;

public class UserNotFound extends NotFoundException {

    public UserNotFound(String message) {
        super(message);
    }

}
