package com.zcc.springboot.spring4test1.service;

import com.zcc.springboot.spring4test1.entity.User;

public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);
}
