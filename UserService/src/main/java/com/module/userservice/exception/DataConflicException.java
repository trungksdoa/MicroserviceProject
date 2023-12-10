package com.module.userservice.exception;

public class DataConflicException extends RuntimeException{


    public DataConflicException() {
        super();
    }

    public DataConflicException(String message) {
        super(message);
    }

    public DataConflicException(Throwable cause) {
        super(cause);
    }

    protected DataConflicException(String message, Throwable cause) {
        super(message, cause);
    }
}
