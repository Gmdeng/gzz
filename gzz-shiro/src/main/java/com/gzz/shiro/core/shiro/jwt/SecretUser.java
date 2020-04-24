package com.gzz.shiro.core.shiro.jwt;

import java.util.HashMap;

public class SecretUser extends HashMap<String, Object> {


    public SecretUser() {

    }
    public SecretUser(String userName, String passwd) {
        put("userName", 0);
        put("passwd", "成功");
    }

    public String getUID() {
        return this.get("uid").toString();
    }

    public void setUID(String uid) {
        put("uid",  uid);
    }

    public String getUserName() {
        return this.get("userName").toString();
    }

    public void setUserName(String userName) {
        put("userName",  userName);
    }

    public String getPasswd() {
        return this.get("passwd").toString();
    }

    public void setPasswd(String passwd) {
        put("passwd", passwd);
    }
}
