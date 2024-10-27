package com.ashman.sample.controller;

import java.time.Instant;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ashman.sample.exception.InvalidFieldException;
import com.ashman.sample.model.BaseResponse;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponse<?>> handleNoSuchElementException(Exception e) {
        BaseResponse<?> response = new BaseResponse<>();
        response.setErrorTime(Instant.now());
        response.setErrorMessage(e.getMessage());
        response.setErrorCode("404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidFieldException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponse<?>> handleInvalidFieldException(Exception e) {
        BaseResponse<?> response = new BaseResponse<>();
        response.setErrorTime(Instant.now());
        response.setErrorMessage(e.getMessage());
        response.setErrorCode("400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
