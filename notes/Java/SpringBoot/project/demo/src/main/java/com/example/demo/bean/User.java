package com.example.demo.bean;


import com.example.demo.bean.validate.Group;

import javax.validation.constraints.NotBlank;

public class User {
    // 分组时使用
//    @NotBlank(message = "手机号不能为空", groups = Group.Update.class)
    @NotBlank(message = "手机号不能为空")
    private String phone;
//    @NotBlank(message = "用户名不能为空", groups = Group.Insert.class)
    @NotBlank(message = "用户名不能为空")
    private String username;
//    @NotBlank(message = "密码不能为空", groups = Group.Insert.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
