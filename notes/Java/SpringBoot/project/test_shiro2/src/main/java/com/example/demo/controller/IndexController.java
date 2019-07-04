package com.example.demo.controller;

import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping({"/index", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping("/err")
    public String err() {
        return "/error/error";
    }

    @RequestMapping("/role")
    @ResponseBody
    public Object test() {
        return roleService.findRoleByUserId(3);
    }
}
