package com.cheng.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by chenghx on 2020/12/21 10:43
 */
public class JwtUtil {

    //签名
    private static final String SIGN = "!QWER^&*";

    /**
     * 获取token
     * @param map
     * @return
     */
    public static String getToken(Map<String,String> map){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7);//7天过期

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    /**
     * 验证token 及获取token中的信息
     * @param token
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

}
