package com.example.demo.service.impl;

import com.example.demo.bean.Permission;
import com.example.demo.dao.PermissionMapper;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionByRole(int id) {
        return permissionMapper.findPermissionByRole(id);
    }
}
