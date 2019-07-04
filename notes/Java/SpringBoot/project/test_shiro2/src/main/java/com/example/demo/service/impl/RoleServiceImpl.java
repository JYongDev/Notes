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
    public List<Role> findRoleById(int id) {
        return roleMapper.findRoleById(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Role> findRoleByUserId(int id) {
        return roleMapper.findRoleByUserId(id);
    }
}
