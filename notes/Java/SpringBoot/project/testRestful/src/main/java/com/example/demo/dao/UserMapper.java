package com.example.demo.dao;

import com.example.demo.bean.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {

    List<Account> selectAll();

    int update(Account user);

    Account query(int id);

    int delete(int id);

    int add(Account user);

}
