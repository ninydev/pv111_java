package com.itstep.first_spring.exceptions.auth;

public class EmailInvalidException extends Exception{
    public EmailInvalidException(String message) {
        super(message);
    }
}
