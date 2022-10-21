package com.lwin.emr.exception;

public class CustomException extends RuntimeException{

    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}
