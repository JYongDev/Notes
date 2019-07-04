package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index() {
        return "index";
    }

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/test")
    public Object test() {
        return userService.selectByName("aaa");
    }

    @RequestMapping("/err")
    public String err() {
        return "error";
    }
}
