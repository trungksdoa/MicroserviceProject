package com.module.userservice.exception;

public class VerifyFailedException extends RuntimeException{
    public VerifyFailedException(String message) {
        super(message);
    }
}
