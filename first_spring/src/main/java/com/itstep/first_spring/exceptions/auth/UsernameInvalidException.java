package com.itstep.first_spring.exceptions.auth;

public class UsernameInvalidException extends Exception
{
    public UsernameInvalidException(String message) {
        super(message);
    }
}
