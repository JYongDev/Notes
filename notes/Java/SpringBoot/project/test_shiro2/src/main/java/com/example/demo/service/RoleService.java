package com.example.demo.service;


import com.example.demo.bean.Role;

import java.util.List;

public interface RoleService {
    List<Role> findRoleById(int id);


    List<Role> selectAll();

    List<Role> findRoleByUserId(int id);
}
