package com.bricks.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.bricks.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        try {
            JWTUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            System.out.println("token无效");
        } catch (TokenExpiredException e) {
            System.out.println("token过期");
        } catch (AlgorithmMismatchException e) {
            System.out.println("算法不匹配");
        } catch (Exception e) {
            System.out.println("token无效");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("state", false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
