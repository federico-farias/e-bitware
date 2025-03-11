package com.example.demo.config;

import com.example.demo.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> businessHandler(NotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
