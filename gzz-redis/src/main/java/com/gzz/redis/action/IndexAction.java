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
        System.out.println(basestr);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            this.redisTemplate.opsForValue().set("HETST", "Ricky Deng");
            this.valueOperations.set("name", "Ricky");
            this.valueOperations.getAndSet("userId", "333");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello Word";
    }

    @GetMapping("/saveUser")
    public String saveUser() {
        logger.info("Save User............................................");
        User user = new User();
        user.setId(100000L);
        user.setName("Ricky");
        user.setAge("99");
        user.setCreateTime(new Date());
        redisTemplate.opsForValue().set(UUID.randomUUID().toString(), user, 30, TimeUnit.MINUTES);
        return "save data";
    }

    @GetMapping("/saveSession")
    public String saveSession() {
        SimpleSession session = new SimpleSession();
        redisTemplate.opsForValue().set(UUID.randomUUID().toString(), session, 30, TimeUnit.MINUTES);
        return "save session";
    }
}
