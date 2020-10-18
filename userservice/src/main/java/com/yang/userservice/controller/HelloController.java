package com.yang.userservice.controller;

import com.yang.userservice.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "userService:" + port;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public User findById(String id) {
        User user = new User();
        user.setId(id);
        user.setName("name:123123");
        user.setPhone("123456789");
        return user;
    }

    @RequestMapping("/find")
    @ResponseBody
    public User find(@RequestBody User user) {
        user.setPhone("测试对象返回");
        return user;
    }

}
