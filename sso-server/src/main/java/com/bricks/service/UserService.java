package com.bricks.service;

import com.bricks.entity.User;

public interface UserService {
    User login(User user);
    Boolean register(User user);
}
