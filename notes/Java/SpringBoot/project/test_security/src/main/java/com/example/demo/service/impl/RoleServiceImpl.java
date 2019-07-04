package com.example.demo.service.impl;

import com.example.demo.bean.Role;
import com.example.demo.dao.RoleMapper;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listByUserId(int id) {
        return roleMapper.listByUserId(id);
    }

    @Override
    public Role listByName(String name) {
        return roleMapper.listByName(name);
    }
}
