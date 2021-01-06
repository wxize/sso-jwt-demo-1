package com.bricks.service;

import com.bricks.dao.UserDAO;
import com.bricks.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        User userDB = userDAO.login(user);
        if (userDB != null) {
            return userDB;
        }
        throw new RuntimeException("登录失败");
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean register(User user) {
        return userDAO.register(user);
    }
}
