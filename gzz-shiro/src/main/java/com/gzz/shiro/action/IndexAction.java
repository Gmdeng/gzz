package com.gzz.shiro.action;

import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@RestController
public class IndexAction {
    @Value("${spring.data.user.age}")
    private String age;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/helloWord")
    public String index(String name) {
        return "Hello Ricky age:" + age + " name:" + name;
    }

    @GetMapping("/saveSession")
    public String saveSession() {
        SimpleSession session = new SimpleSession();
        session.setId(111111111111111111l);
        redisTemplate.opsForValue().set(UUID.randomUUID().toString(), session, 30, TimeUnit.MINUTES);
        return "save session";
    }
}
