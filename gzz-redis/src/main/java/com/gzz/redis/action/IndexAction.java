package com.gzz.redis.action;

import com.gzz.redis.entity.SimpleSession;
import com.gzz.redis.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@RestController
@RequestMapping("/")
public class  IndexAction {
    private final Logger logger = LogManager.getLogger(LogManager.FACTORY_PROPERTY_NAME);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, Object> valueOperations;
    
    
    @GetMapping("/index")
    public String index() {
        logger.info("Hellow............................................");

        String basestr = Base64.getEncoder().encodeToString("我是一个好人".getBytes());
        logger.info("Base64: "+basestr);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            this.redisTemplate.opsForValue().set("HETST", "Ricky Deng");
            this.valueOperations.set("name", "Ricky New Test");
            this.valueOperations.set("NewTestMAN", "Ricky New Test");
            this.valueOperations.getAndSet("userId", "333");
            this.redisTemplate.expire("name", 30, TimeUnit.SECONDS);

        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "Hello Word";
    }

    @GetMapping("/saveUser")
    public String saveUser() {
        logger.info("Save User............................................");
        User user = new User();
        user.setId(100000L);
        user.setName("Ricky");
        user.setAge(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        redisTemplate.opsForValue().set("342dde2e-aa26-4caa-8c2c-3bfe6e1bc43d", user, 30, TimeUnit.MINUTES);
        return "save data";
    }

    @GetMapping("/getUser")
    public Object getUser() {

        //redisTemplate.opsForValue().get("342dde2e-aa26-4caa-8c2c-3bfe6e1bc43d");
        //Object obj = redisTemplate.opsForValue().get(getKey(id));
        Object obj = redisTemplate.boundValueOps("342dde2e-aa26-4caa-8c2c-3bfe6e1bc43d").get();
        User user = (User) obj;
        user.setGuid(UUID.randomUUID().toString().replace("-", ""));
        return user;
    }

    @GetMapping("/saveSession")
    public String saveSession() {
        SimpleSession session = new SimpleSession();
        redisTemplate.opsForValue().set("3bfe6e1bc43d", session, 30, TimeUnit.MINUTES);
        return "save session";
    }

    @GetMapping("/getSession")
    public Object getSession() {
        Object obj = redisTemplate.opsForValue().get("3bfe6e1bc43d");
        SimpleSession session = (SimpleSession) obj;
        return session;
    }
}
