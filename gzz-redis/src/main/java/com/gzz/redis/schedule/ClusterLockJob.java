package com.gzz.redis.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@EnableScheduling //启动定时任务
@Component
public class ClusterLockJob {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static String LOCK_PREFIX = "prefix_";
    private static int LOCK_EXPIRE =100;
    @Scheduled(cron="*/5 * * * * *")
    public void lock() {
        System.out.println("进入锁LOCK JOB");
        String lock = LOCK_PREFIX + "_LockNxExJob";
        try {
            boolean nxRet = redisTemplate.opsForValue().setIfAbsent(lock,"");
            Object lockValue = redisTemplate.opsForValue().get(lock);

            //获取锁失败
            if(!nxRet) {
                String value = (String) lockValue;
                System.out.println("获取失败，被lock belong to" + value);
                return;
            }
            redisTemplate.opsForValue().set(lock, "", 3600);
            //获取锁成功
            System.out.println("处理业务。");
            Thread.sleep(5000l);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("释放锁LOCK JOB");
            redisTemplate.delete(lock);
        }

    }

    public boolean luaExpress(String key, String value) {
        DefaultRedisScript<Boolean> lockScript = new DefaultRedisScript<>();
        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("add.lua")));
        lockScript.setResultType(Boolean.class);

        //
        List<Object> keyList = new ArrayList<>();
        keyList.add(key);
        keyList.add(value);
        Boolean result = false;
        
        return  result;
    }
}
