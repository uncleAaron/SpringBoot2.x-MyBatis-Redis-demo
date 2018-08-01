package com.example.controller;

import com.example.exceptions.UserIsExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserIsExistException.class)
    public Map<String, Object> userExistExceptionHandler(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 405);
        map.put("message", e.getMessage());
        return map;
    }

}
