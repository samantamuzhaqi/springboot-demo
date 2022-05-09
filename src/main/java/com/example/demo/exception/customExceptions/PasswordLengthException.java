package com.example.demo.exception.customExceptions;

public class PasswordLengthException extends Throwable{
    public PasswordLengthException(String msg){
        super(msg);
    }
    public PasswordLengthException (String msg, Throwable cause){
        super(msg, cause);
    }
}
