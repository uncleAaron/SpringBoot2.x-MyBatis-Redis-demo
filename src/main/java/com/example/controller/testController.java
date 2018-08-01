package com.example.controller;


import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/test")
public class testController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/testA")
    public String testA(Model model){
        model.addAttribute("test", "test success");
        return "test";
    }

    @RequestMapping("/testB")
    public String testB(
            Model model,
            @RequestParam("id") int id){
        model.addAttribute("test", userMapper.findUser(id));
        return "test";
    }

    @RequestMapping("/input")
    public String input(){
        return "input";
    }

    @RequestMapping("/listall")
    public String listAll(Model model){
        List<User> users = userMapper.findAllUser();
        model.addAttribute("list", users);
        return "listall";
    }

    @PostMapping("/inputvalue")
    public String getInput(User user,
                           Model model) {
        model.addAttribute("test",user);
        userMapper.addUser(user);
        return "test";
    }

    @ExceptionHandler
    public String ExceptionHandler(Model model, Exception ex) {
        model.addAttribute("test", "出错了");
        return "test";
    }
}
