package com.example.demo.config;

import com.example.demo.bean.Permission;
import com.example.demo.bean.Role;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        // 获得loadUserByUsername()方法的结果
        User user = (User) authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        // 遍历用户所有角色
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();

            Role role = roleService.listByName(roleName);

            if (role != null) {
                // 得到角色所有的权限
                List<Permission> permissions = permissionService.selectByRoleId(role.getId());
                List<String> pName = permissions.stream().map(Permission::getName).collect(Collectors.toList());

                if (pName.contains((String) o1)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
