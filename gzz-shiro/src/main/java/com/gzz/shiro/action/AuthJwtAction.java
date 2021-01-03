package com.gzz.shiro.action;

import com.gzz.shiro.core.HttpResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证授权
 */
@RestController
@RequestMapping("/authjwt")
public class AuthJwtAction {
    private final Logger logger = LogManager.getLogger();
    /**
     * 登录处理
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
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
     *
     * @param request
     * @return
     */
    @GetMapping("/refreshToken")
    public Object RefreshToken(HttpServletRequest request){
        logger.info("刷新TOKEN");
        return HttpResult.success();
    }
}
