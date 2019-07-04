package com.example.demo.service;


import com.example.demo.bean.Role;

import java.util.List;

public interface RoleService {

    List<Role> listByUserId(int id);

    Role listByName(String name);
}
