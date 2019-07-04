package com.example.demo;

import com.example.demo.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestShiro2ApplicationTests {

    @Autowired
    private RoleService roleService;

    @Test
    public void contextLoads() {
    }

    @Test
    @ResponseBody
    public void testRole() {
        roleService.selectAll();
    }

}
