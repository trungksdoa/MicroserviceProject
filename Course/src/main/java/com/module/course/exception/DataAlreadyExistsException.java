package com.module.course.exception;

public class DataAlreadyExistsException extends RuntimeException{


    public DataAlreadyExistsException() {
        super();
    }

    public DataAlreadyExistsException(String message) {
        super(message);
    }

    public DataAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected DataAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
