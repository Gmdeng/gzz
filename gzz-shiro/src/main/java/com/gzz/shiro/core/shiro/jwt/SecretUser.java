package com.gzz.shiro.core.shiro.jwt;

public class SecretUser {
    private String userName;
    private String passwd;

    public SecretUser() {
    }
    public SecretUser(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
