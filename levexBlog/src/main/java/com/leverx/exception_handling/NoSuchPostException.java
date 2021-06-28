package com.leverx.exception_handling;

public class NoSuchPostException extends  RuntimeException{
    public NoSuchPostException(String message) {
        super(message);
    }
}
