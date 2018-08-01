package com.example.exceptions;

public class BaseError extends Exception {

    private String message;
    private int code;

    public BaseError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
