package com.jotapem.youraccount.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.add("Field:" + fieldError.getField().toUpperCase() + " - " + fieldError.getDefaultMessage()));
        exception.getBindingResult().getGlobalErrors()
                .forEach(globalError -> errors.add("Object: " + globalError.getObjectName() + " - " + globalError.getDefaultMessage()));

        return buildResponseEntity(HttpStatus.BAD_REQUEST, "", errors);
    }

    @ExceptionHandler(EntityExistsException.class)
    private ResponseEntity<Object> handlerEntityExistsException(EntityExistsException exception) {
        return buildResponseEntity(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return buildResponseEntity(
                HttpStatus.BAD_REQUEST,
                "JSON BODY malformed and/or error found",
                Collections.singletonList(ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundException exception) {
        return buildResponseEntity(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String message, List<String> errors) {
        ApiError apiError = ApiError.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(httpStatus).body(apiError);
    }
}
