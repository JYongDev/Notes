package com.example.demo.service;


import com.example.demo.bean.User;

public interface UserService {
    User selectByName(String username);
}
