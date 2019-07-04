package com.example.demo.service;

import com.example.demo.bean.User;

public interface UserService {

    void addUser(User user);

    User findUserByName(String name);

}
