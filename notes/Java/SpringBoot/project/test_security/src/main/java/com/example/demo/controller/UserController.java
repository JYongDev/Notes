package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password)) {
            return "redirect:/err";
        }

        User user = userService.selectByName(username);
        if (user == null) {
            return "redirect:/err";
        }

        if (user.getUsername().equals(username) &&
                user.getPassword().equals(password)) {
            return "redirect:/logSuc";
        } else {
            return "redirect:/err";
        }
    }

    @RequestMapping("/logSuc")
    public String logSuccess() {
        return "loginSuccess";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password)) {
            return "redirect:/err";
        }


        User user = userService.selectByName(username);
        if (user == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(new BCryptPasswordEncoder().encode(password));
            userService.addUser(newUser);
            return "redirect:/login";
        } else {
            return "redirect:/err";
        }
    }

    @RequestMapping("/loginFail")
    public String loginFail() {
        return "loginFail";
    }
}
