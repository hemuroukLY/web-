package com.ly.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class jwtUtils {
    private static String signKey = "helloTeacher";
    private static Long expire = 1000L * 60 * 60 * 5;
    //生成jwt
    public static String getJwt(Map<String,Object> map){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)//算法和密钥
                .setClaims(map)//数据
                .setExpiration(new Date(System.currentTimeMillis() + expire))//有效期
                .compact();
        return jwt;
    }

    //解析jwt
    public static Claims parseJwt(String jwt){
        Claims body = Jwts.parser()
                .setSigningKey(signKey)//密钥
                .parseClaimsJws(jwt)//令牌
                .getBody();
        return body;
    }
}
