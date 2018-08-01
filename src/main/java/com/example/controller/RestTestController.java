package com.example.controller;

import com.example.exceptions.UserNotFoundException;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rest")
public class RestTestController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 必须是请求头Context-Type = application/json 的Get 请求才可以响应
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.GET,
            consumes = "application/json")
    public User findUser(@PathVariable("id") int id) {
        User user = userMapper.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @RequestMapping(
            value = "/testwithURL/user/{id}",
            method = RequestMethod.GET,
            consumes = "application/json")
    public ResponseEntity<User> findUserWithURL(
            @PathVariable("id") int id,
            UriComponentsBuilder ucb) {     //参数要引用UriComponentsBuilder

        User user = userMapper.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(id);    //抛出找不到的异常，交给后面的异常处理器返回错误消息
        }

        HttpHeaders headers = new HttpHeaders();    //新建请求头
        URI locationUri = ucb.path("/rest/user/")   //计算Location URL
                .path(String.valueOf(user.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);           //请求头设置上URL

        return new ResponseEntity<User>(user, headers, HttpStatus.CREATED); //返回ResponseEntity，注意看参数，有对象，请求头，和HTTP状态码
    }

    /**
     * 参数记得要引入Exception对象
     * @param ex
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public com.example.exceptions.BaseError userNotFound(UserNotFoundException ex){
        return new com.example.exceptions.BaseError(4, "User[" + ex.getUserID() + "] Not Found.");
    }

    @RequestMapping("/test")
    public String test(){
        return "test success rest";
    }
}
