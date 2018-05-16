package com.zcc.springboot.spring4test1.service;

import com.zcc.springboot.spring4test1.entity.User;
import com.zcc.springboot.spring4test1.mapper.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;
    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(User record) {
        return false;
    }
}
