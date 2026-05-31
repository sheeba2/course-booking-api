package com.course_booking_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    Map<String,Object>handleNotFound(ResourceNotFoundException ex){
        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        error.put("status",404);
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    public Map<String,Object> handleGeneric(Exception ex){
        Map<String,Object> error = new HashMap<>();
        error.put("message","Something went wrong");
        error.put("status",500);
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleValidation(Exception ex){
        Map<String,Object> error = new HashMap<>();
        error.put("message","Validation Failed");
        error.put("status:",400);

        return error;
    }

    @ExceptionHandler(BookingConflictException.class)
    public ResponseEntity<?> handleBookingConflict(BookingConflictException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error",ex.getMessage()));

    }
}
