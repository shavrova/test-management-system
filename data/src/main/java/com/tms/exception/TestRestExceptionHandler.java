package com.tms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TestRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ItemNotFoundException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
//        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotAllowedException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(IllegalStateException exc) {
//        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), "Entity is already exists.");
//        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
//    }
}





