package com.gzz.shiro.core;

import com.gzz.shiro.core.shiro.jwt.JwtUtil;
import com.gzz.shiro.core.shiro.jwt.SecretUser;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.Date;

public class TestJwtUtil {

    @Test
    public void testGenToken(){

        SecretUser secretUser = new SecretUser();
        secretUser.setUserName("小明");
        secretUser.setPasswd("passs");
        secretUser.put("createOn", new Date());
        String token = JwtUtil.createToken(secretUser);
        System.out.println("Testing");
        System.out.println("token令牌是："+token);

       String retoken = JwtUtil.refreshToken(token);
       System.out.println("刷新后的令牌是："+retoken);
        // 还原成Claims
        Claims claims1 = JwtUtil.parseJWT(token);

        System.out.println("u====================================================");
        System.out.println("username参数值：" + claims1.get("username"));
        System.out.println("登录用户的id：" + claims1.getId());
        System.out.println("登录用户的名称：" + claims1.getSubject());
        System.out.println("令牌签发时间：" + claims1.getIssuedAt());
        System.out.println("令牌过期时间：" + claims1.getExpiration());

        // 还原成Claims
        Claims claims2 = JwtUtil.parseJWT(retoken);
        System.out.println("u====================================================");
        System.out.println("username参数值：" + claims2.get("username"));
        System.out.println("登录用户的id：" + claims2.getId());
        System.out.println("登录用户的名称：" + claims2.getSubject());
        System.out.println("令牌签发时间：" + claims2.getIssuedAt());
        System.out.println("令牌过期时间：" + claims2.getExpiration());
    }
}
