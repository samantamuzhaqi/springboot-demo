package com.example.demo.exception.customExceptions;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(
            String msg) {
        super(msg);
    }

    public UserNotFoundException(
            String msg, Throwable cause) {
        super(msg, cause);
    }

}
