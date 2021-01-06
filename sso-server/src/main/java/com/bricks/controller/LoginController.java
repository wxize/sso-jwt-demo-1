package com.bricks.controller;

import com.bricks.entity.User;
import com.bricks.service.UserService;
import com.bricks.utils.JWTUtil;
import com.bricks.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Res login(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
//            生成JWT令牌
            Map<String,String> payload = new HashMap<>();
            payload.put("id", userDB.getId()+"");
            payload.put("username", userDB.getUsername());
            String token = JWTUtil.getToken(payload);
            map.put("token", token);
            return Res.build(0, "登录成功", map);
        }catch (Exception e) {
            return Res.build(1, "登录失败");
        }
    }
}
