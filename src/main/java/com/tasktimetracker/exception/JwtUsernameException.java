package com.tasktimetracker.exception;

public class JwtUsernameException extends RuntimeException {
    public JwtUsernameException(String message) {
        super(message);
    }
}
