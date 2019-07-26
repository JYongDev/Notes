package com.example.demo.web;

import com.example.demo.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestRedisController {

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping("/save")
    public boolean save() {
        try {
            redisService.setExp("password","haha",10L);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @ResponseBody
    @RequestMapping("/get")
    public Object get() {
        return redisService.get("password");
    }

}
