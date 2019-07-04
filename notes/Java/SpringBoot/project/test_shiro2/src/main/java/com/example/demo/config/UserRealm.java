package com.example.demo.config;


import com.example.demo.bean.Permission;
import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();

        User userResult = userService.findUserByName(user.getName());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Role> roleList = roleService.findRoleByUserId(userResult.getId());

        for (Role role : roleList) {
            info.addRole(role.getName());
            List<Permission> permissions = permissionService.findPermissionByRole(role.getId());
            for (Permission p : permissions) {
                info.addStringPermission(p.getName());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.findUserByName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user,
                    user.getPsw(),
                    // 盐值，加大密码被破解的难度
                    ByteSource.Util.bytes("123123"),
                    getName());
        } else {
            return null;
        }
    }
}
