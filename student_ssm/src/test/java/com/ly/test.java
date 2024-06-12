package com.ly;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test {
    @Test
    public void getJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","login");
        claims.put("password","123456");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "helloTeacher")//算法和密钥
                .setClaims(claims)//数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 10))//有效期
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void parseJwt(){
        Claims body = Jwts.parser()
                .setSigningKey("helloTeacher")//密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImlkIjoxLCJleHAiOjE3MTc2ODcwMzAsInVzZXJuYW1lIjoibG9naW4ifQ.G51mC45egkL-2xfk_LczFubgB3OVmPdUib3xd8eUwgI")//令牌
                .getBody();
        System.out.println(body);
    }



}
