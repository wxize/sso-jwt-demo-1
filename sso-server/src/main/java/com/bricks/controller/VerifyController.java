package com.bricks.controller;

import com.bricks.utils.JWTUtil;
import com.bricks.utils.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class VerifyController {

    @GetMapping("/verify")
    public Res verify(HttpServletRequest request) {
//        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        try {
            JWTUtil.verify(token);
            return Res.build(0, "有效令牌");
//            map.put("state", true);
//            User userDB = userService.login(user);
////            生成JWT令牌
//            Map<String,String> payload = new HashMap<>();
//            payload.put("id", userDB.getId()+"");
//            payload.put("username", userDB.getUsername());
//            String token = JWTUtil.getToken(payload);
//            map.put("state", true);
//            map.put("msg", "登录成功");
//            map.put("token", token);
        }catch (Exception e) {
//            map.put("state", false);
//            Res.build(2, "无效令牌");
//            map.put("msg", e.getMessage());
        }
        return Res.build(2, "无效令牌");
    }
}
