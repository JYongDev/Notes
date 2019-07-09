package com.example.demo.controller;

import com.example.demo.bean.Account;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestfulController {


    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    public Object getUserList() {
        return userService.selectAll();
    }

    @GetMapping(value = "/user")
    public Object queryUser(@RequestParam int id) {
        return userService.query(id);
    }

    @DeleteMapping(value = "/user")
    public Object deleteUser(@RequestParam int id) {
        return userService.delete(id);
    }

    @PutMapping(value = "/user")
    public Object updateUser(@RequestParam String password, @RequestParam int id) {
        Account user = new Account();
        user.setPassword(password);
        user.setId(id);
        return userService.update(user);
    }

    @PostMapping(value = "/user")
    public Object addUser(@RequestParam String username, @RequestParam String password) {
        Account user = new Account();
        user.setUsername(username);
        user.setPassword(password);
        return userService.add(user);
    }


}
