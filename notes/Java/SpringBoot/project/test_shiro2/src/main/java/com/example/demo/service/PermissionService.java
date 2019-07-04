package com.example.demo.service;

import com.example.demo.bean.Permission;

import java.util.List;


public interface PermissionService {
    List<Permission> findPermissionByRole(int id);
}
