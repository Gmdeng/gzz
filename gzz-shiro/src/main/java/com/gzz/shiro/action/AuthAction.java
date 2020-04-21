package com.gzz.shiro.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证授权
 */
@Controller
@RequestMapping("/auth")
public class AuthAction {
    private final Logger logger = LogManager.getLogger();

    /**
     * 登录页面
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/login")
    public String login(HttpServletRequest request) {

        String preUrl = request.getHeader("Referer");
        logger.info("登录" + preUrl);
        logger.info("密码：" + md5("passwd", "htest"));
        return "auth/login";
    }

    /**
     * 登录处理
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String doLogin(HttpServletRequest request) {
        String errMsg = "生成基于用户名和密码的令牌0000"; //

        Subject subject = SecurityUtils.getSubject();
        // 生成基于用户名和密码的令牌
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        try {
            // 登录前url
            String firstUrl = savedRequest.getRequestUrl();
            logger.info("登录URL {}", firstUrl);
            // 如果未使用接口方式，可以直接跳转url并清空，使用WebUtils中的redirectToSavedRequest方法
            WebUtils.getAndClearSavedRequest(request);
        } catch (NullPointerException e) {
            logger.info("没有跳转过来的，直接跳转到默认主页");
        }
        return errMsg;
    }

    /**
     * 密码加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static final String md5(String password, String salt) {
        //加密方式
        String algorithmName = "MD5";
        //盐值，用于和密码混合起来用
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //加密的次数,可以进行多次的加密操作
        int hashIterations = 10;
        //通过SimpleHash 来进行加密操作
        SimpleHash result = new SimpleHash(algorithmName, password, byteSalt, hashIterations);
        return result.toString();
    }
}
