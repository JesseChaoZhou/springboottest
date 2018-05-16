package com.zcc.springboot.spring4test1.controller;

import com.zcc.springboot.spring4test1.entity.User;
import com.zcc.springboot.spring4test1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/showUser")
    public User testController(int id){
        User user = this.userService.getUserById(id);
        return user;
    }
}
