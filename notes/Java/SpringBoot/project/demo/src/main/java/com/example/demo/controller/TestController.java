package com.example.demo.controller;

import com.example.demo.bean.Response;
import com.example.demo.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


@Controller
public class TestController {


    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(@Valid User user) {
        Response<User> response = new Response<>();
        response.setCode(200);
        response.setMessage("登录成功");
        response.setData(user);
        return response;
    }
}
