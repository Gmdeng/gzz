package com.gzz.shiro.core.shiro.jwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class JwtCredentialsMatcher implements CredentialsMatcher {
    private final Logger logger = LogManager.getLogger();
    private static final String RETRYLIMIT_PREFIX = "shiro:cache:retrylimit:";
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * Matcher中直接调用工具包中的verify方法即可
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = (String) ((JwtToken)authenticationToken).getToken();
        SecretUser user = (SecretUser)authenticationInfo.getPrincipals().getPrimaryPrincipal();

        //得到DefaultJwtParser
        Boolean verify = false;// JwtUtil.isVerify(token, user);
        logger.info("JWT密码效验结果="+verify);
        return verify;
    }
    /**
     * 根据用户名 解锁用户
     *
     * @param username
     * @return
     */
    public void unlockAccount(String username) {
//		User user = userMapper.findByUserName(username);
//		if (user != null) {
//			// 修改数据库的状态字段为锁定
//			user.setState("0");
//			userMapper.update(user);
        redisTemplate.delete(getRetrylimitKey(username));
//		}
    }

    private String getRetrylimitKey(String username) {
        return RETRYLIMIT_PREFIX + username;
    }
}
