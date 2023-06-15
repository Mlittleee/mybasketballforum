package com.project.mybasketballforum.exception;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(String message){
        super(message);
    }
}
