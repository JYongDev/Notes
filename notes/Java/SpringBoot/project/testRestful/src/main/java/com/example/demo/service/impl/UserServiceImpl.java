package com.example.demo.service.impl;

import com.example.demo.bean.Account;
import com.example.demo.dao.UserMapper;
import com.example.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Account> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int update(Account user) {
        return userMapper.update(user);
    }

    @Override
    public Account query(int id) {
        return userMapper.query(id);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public int add(Account user) {
        return userMapper.add(user);
    }
}
