package com.example.demo.service;


import com.example.demo.bean.Account;

import java.util.List;

public interface UserService {

    List<Account> selectAll();

    int update(Account user);

    Account query(int id);

    int delete(int id);

    int add(Account user);

}
