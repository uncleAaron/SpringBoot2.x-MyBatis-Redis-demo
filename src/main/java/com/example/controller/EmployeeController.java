package com.example.controller;

import com.example.entity.User;
import com.example.exceptions.UserIsExistException;
import com.example.mapper.ClassMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    UserService userService;

    @Autowired
    ClassMapper classMapper;

    @GetMapping("/emps")
    public String getAllUser (Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        model.addAttribute("classes",classMapper.findAllClasses());  //在add页面上列出已有的班级
        return "emp/add";
    }

    @PostMapping("emp")
    public String addUser (User user) {
        try {
            userService.addUser(user);
        } catch (RuntimeException ex) {
            throw new UserIsExistException();
        }
        // redirect: 重定向到一个地址
        // forward: 转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面（重用添加页面），查出当前员工，然后在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage (@PathVariable("id") Integer id, Model model) {
        model.addAttribute("classes",classMapper.findAllClasses()); //在add页面上列出已有的班级
        model.addAttribute("user", userService.getUser(id));    //回显User数据
        return "emp/add";
    }

    @PutMapping("/emp")
    public String editUser (User user) {
        userService.updateUser(user);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteUser (@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/emps";
    }
}
