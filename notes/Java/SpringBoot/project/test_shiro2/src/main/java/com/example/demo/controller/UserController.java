package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "redirect:/err";
        }

        User user = new User();
        user.setName(username);
        user.setPsw(password);

        ByteSource salt = ByteSource.Util.bytes("123123");
        /*
        * MD5加密：
        * 使用SimpleHash类对原始密码进行加密。
        * 第一个参数代表使用MD5方式加密
        * 第二个参数为原始密码
        * 第三个参数为盐值，即用户名
        * 第四个参数为加密次数
        * 最后用toHex()方法将加密后的密码转成String
        * */
        String newPSW = new SimpleHash("MD5", password, salt, 2).toHex();
        user.setPsw(newPSW);
        userService.addUser(user);

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password);
        //  进行验证，这里可以捕获异常，然后返回对应信息
        //  配置了SimpleMappingExceptionResolver，可不在这里处理异常
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            // 用户不存在
            return "redirect:/err";
        } catch (IncorrectCredentialsException e1) {
            e1.printStackTrace();
            // 密码或用户名错误
            return "redirect:/err";
        } catch (Exception e) {
            return "redirect:/err";
        }
        return "redirect:/info";
    }

    @RequestMapping("/info")
    public String info() {
        return "success";
    }

}
