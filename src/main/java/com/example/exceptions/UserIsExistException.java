package com.example.exceptions;

public class UserIsExistException extends RuntimeException {

    public UserIsExistException() {
        super("用户已存在");
        System.out.println("用户已存在");

    }
}
