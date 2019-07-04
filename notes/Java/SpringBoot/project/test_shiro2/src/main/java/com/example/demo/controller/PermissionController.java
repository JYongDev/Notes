package com.example.demo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PermissionController {

    @RequestMapping("/admin")
    @RequiresRoles("admin")
    public Object testAdmin() {
        return " admin page";
    }

    @RequestMapping("/user")
    @RequiresRoles("user")
    public Object testUser() {
        return " user page ";
    }

    @RequiresRoles("visitor")
    @RequestMapping("/visitor")
    public Object testVisitor() {
        return "visitor page";
    }


    @RequiresPermissions("read")
    @RequestMapping("/read")
    public Object testRead() {
        return "read page";
    }

    @RequiresPermissions("write")
    @RequestMapping("/write")
    public Object testWrite() {
        return "write page";
    }

    @RequiresPermissions("execute")
    @RequestMapping("/execute")
    public Object testExecute() {
        return "execute page";
    }


}
