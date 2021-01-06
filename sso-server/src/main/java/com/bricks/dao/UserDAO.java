package com.bricks.dao;

import com.bricks.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    User login(User user);
    Boolean register(User user);
}
