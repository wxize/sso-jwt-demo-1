package com.bricks;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

//@SpringBootTest
class SpringbootjwtApplicationTests {

    public static void main(String[] args) {
//        generateToken();
        verifilerToken();
    }

    public static void generateToken() {
        Calendar ins = Calendar.getInstance();
        ins.add(Calendar.SECOND, 6000);

        String token = JWT.create()
                .withClaim("userId", 21)
                .withClaim("username", "test001")
                .withExpiresAt(ins.getTime()) // 过期时间，20s后过期
                .sign(Algorithm.HMAC256("@签名#"));

        System.out.println(token);
    }

    public static void verifilerToken() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("@签名#")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDkxMjk1OTksInVzZXJJZCI6MjEsInVzZXJuYW1lIjoidGVzdDAwMSJ9.2wZSsIf-mtscCa6qFrSZPR2dHNhRW7hB4uAuguI_r3M");
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("username").asString());
    }
}
