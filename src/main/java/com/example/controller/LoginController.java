package com.example.controller;

import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        if (!StringUtils.isEmpty(username) && password.equals("123456")) { // 登陆成功
            session.setAttribute("loginUser", username);
            // 使用重定向到主页，以防止表单重复提交
            // 下面这个/main.html已经在WebMvcConfig中注册了视图映射，从/main.html映射到dashboard
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "登陆失败");
            return "login";
        }
    }
}
