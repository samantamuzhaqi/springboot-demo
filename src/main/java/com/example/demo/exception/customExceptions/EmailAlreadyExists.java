package com.example.demo.exception.customExceptions;

public class EmailAlreadyExists extends Throwable{
    public EmailAlreadyExists (String msg){
        super(msg);
    }
    public EmailAlreadyExists (String msg, Throwable cause){
        super(msg, cause);
    }
}
