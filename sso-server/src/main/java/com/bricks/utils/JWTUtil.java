package com.bricks.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil {

    private static final String SIGN_KEY = "_!@s#k$c%i^r&b*";

    /**
     * 生成token
     * @param map payload.sign
     * @return token
     */
    public static String getToken(Map<String,String> map) {
        Calendar ins = Calendar.getInstance();
        ins.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();

        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        return builder.withExpiresAt(ins.getTime())
                .sign(Algorithm.HMAC256(SIGN_KEY));
    }

    /**
     * 验证token的合法性
     * @param token
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN_KEY)).build().verify(token);
    }
}
