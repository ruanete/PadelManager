package com.ruanete.padelmanager.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestException(MethodArgumentTypeMismatchException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(false, "Error in values passed to endpoint, possibly due to data type error.");
	    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(false, "Error in values passed to endpoint, possibly due to data type error or values are not correct.");
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(TrackNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(TrackNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(false, ex.getMessage());
	    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TrackErrorException.class)
	public final ResponseEntity<ExceptionResponse> handleErrorException(TrackErrorException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(false, ex.getMessage());
	    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
