package com.bricks.controller;

import com.bricks.entity.User;
import com.bricks.service.UserService;
import com.bricks.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Res register(User user) {
//        Map<String, Object> map = new HashMap<>();
        try {
            Boolean bool = userService.register(user);
            if (bool) {
//                map.put("state", true);
//                map.put("msg", "注册成功");
                return Res.build(0, "注册成功");
            }
        }catch (Exception e) {
//            map.put("state", false);
//            map.put("msg", e.getMessage());

        }
        return Res.build(1, "注册失败");
    }
}
