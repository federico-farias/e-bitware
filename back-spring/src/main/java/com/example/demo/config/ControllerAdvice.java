package com.example.demo.config;

import com.example.demo.dto.ErrorResponseDTO;
import com.example.demo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> businessHandler(NotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatusCode.valueOf(404));
    }

}
