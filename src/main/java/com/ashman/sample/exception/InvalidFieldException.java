package com.ashman.sample.exception;

public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException(String field) {
        super("Invalid field: " + field);
    }
}
