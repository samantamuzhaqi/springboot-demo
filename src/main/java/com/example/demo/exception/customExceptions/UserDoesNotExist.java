package com.example.demo.exception.customExceptions;

public class UserDoesNotExist extends Throwable{
    public UserDoesNotExist(
            String msg){
        super(msg);
    }
    public UserDoesNotExist
            (String msg, Throwable cause){
        super(msg, cause);
    }
}
