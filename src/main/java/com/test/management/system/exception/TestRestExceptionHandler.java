package com.test.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TestRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<TestErrorResponse> handleException(TestNotFoundException exc) {
		TestErrorResponse error = new TestErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<TestErrorResponse> handleException(Exception exc) {
		TestErrorResponse error = new TestErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}





