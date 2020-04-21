package com.gzz.shiro.core.shiro.jwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class JwtCredentialsMatcher implements CredentialsMatcher {
    private Logger logger = LogManager.getLogger(JwtCredentialsMatcher.class);

    /**
     * Matcher中直接调用工具包中的verify方法即可
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = (String) ((JwtToken)authenticationToken).getToken();
        SecretUser user = (SecretUser)authenticationInfo.getPrincipals().getPrimaryPrincipal();
        //得到DefaultJwtParser
        Boolean verify = JwtUtil.isVerify(token, user);
        logger.info("JWT密码效验结果="+verify);
        return verify;
    }
}
