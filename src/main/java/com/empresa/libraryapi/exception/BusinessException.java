package com.empresa.libraryapi.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String Mesagem) {
        super(Mesagem);
    }
}
