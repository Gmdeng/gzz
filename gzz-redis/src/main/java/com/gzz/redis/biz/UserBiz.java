package com.gzz.redis.biz;

import com.google.common.collect.Lists;
import com.gzz.redis.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserBiz {

    @Cacheable
    public User getUser(String uid) {
        User user = new User();
        user.setId(100000L);
        user.setName("Ricky");
        user.setAge("99");
        user.setGuid(uid);
        user.setCreateTime(new Date());
        return user;
    }

    @Cacheable
    public List<User> getList(String gid){
        List<User> userList = Lists.newArrayList();
        for (int i = 0; i < 8; i++) {
            User user = new User();
            long id = 100000+i;
            user.setId(id);
            user.setName("Ricky" +i);
            user.setAge("99");
            user.setGuid(gid);
            user.setCreateTime(new Date());
            userList.add(user);
        }
        return userList;
    }
}
