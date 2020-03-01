package com.gzz.console.service;

import com.gzz.console.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    public UserVo getUser(){
        UserVo user = new UserVo();
        user.setUid(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        user.setName("李冰冰");
        user.setPasswd("123456789");
        return user;
    }
}
