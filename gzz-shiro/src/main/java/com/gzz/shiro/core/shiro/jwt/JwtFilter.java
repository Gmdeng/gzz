package com.gzz.shiro.core.shiro.jwt;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * BasicHttpAuthenticationFilter继承 AuthenticatingFilter 过滤器
 * 其能够自动地进行基于所述传入请求的认证尝试。
 * BasicHttpAuthenticationFilter 基本访问认证过滤器
 * 此实现是每个基本HTTP身份验证规范的Java实现
 * 通过此过滤器得到HTTP请求资源获取Authorization传递过来的token参数
 * 获取subject对象进行身份验证
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private final Logger logger = LogManager.getLogger(JwtFilter.class);

    /**
     * 应用的HTTP方法列表配置基本身份验证筛选器。
     * 获取 request 请求 拒绝拦截登录请求
     * 执行登录认证方法
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.equals("/user/login/verifyUser") || requestURI.equals("/user/register")) {
            return true;
        } else {
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    /**
     * Authorization携带的参数为token
     * JwtToken实现了AuthenticationToken接口封装了token参数
     * 通过getSubject方法获取 subject对象
     * login()发送身份验证
     * <p>
     * 为什么需要在Filter中调用login,不能在controller中调用login?
     * 由于Shiro默认的验证方式是基于session的，在基于token验证的方式中，不能依赖session做为登录的判断依据．
     *
     * @param request
     * @param response
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String token = httpServletRequest.getHeader("Authorization");
            JwtToken jwtToken = new JwtToken(token);
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            Subject subject = getSubject(request, response);
            subject.login(jwtToken);
            logger.info("JWT验证用户信息成功");
            // 如果没有抛出异常则代表登入成功，返回true
            return true;
        } catch (Exception e) {
            /* *
             *  这个问题纠结了好久
             *      原生的shiro验证失败会进入全局异常 但是 和JWT结合以后却不进入了  之前一直想不通
             *      原因是 JWT直接在过滤器里验证  验证成功与否 都是直接返回到过滤器中 成功在进入controller
             *      失败直接返回进入springboot自定义异常处理页面
             */
            JSONObject responseJSONObject = new JSONObject();
            responseJSONObject.put("result", "401");
            responseJSONObject.put("resultCode", "token无效，请重新获取。");
            responseJSONObject.put("resultData", "null");
            responseJSONObject.put("resultTime", TimeUnit.MICROSECONDS.toString());
            PrintWriter out = null;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            logger.info("返回是");
            logger.info(responseJSONObject.toString());
            out = httpServletResponse.getWriter();
            out.append(responseJSONObject.toString());
        }
        return false;
    }


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-control-Allow-Origin", httpRequest.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));


        if (httpResponse.getHeader(HttpHeaders.ORIGIN) != null) {
            /*
            //允许所有域名的脚本访问该资源
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            //表明服务器允许请求中携带字段 ，指明了实际请求中允许携带的首部字段
            httpResponse.setHeader("Access-Control-Allow-Headers",
                    "User-Agent,Origin,Cache-Control,Content-type,Date,Server,withCredentials,AccessToken");
            //指定了当浏览器的credentials设置为true时是否允许浏览器读取response的内容
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            //表明服务器允许客户端使用 POST, GET等 方法发起请求
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            //表明该响应的有效时间为 86400 秒 指定了preflight请求的结果能够被缓存多久
            httpResponse.setHeader("Access-Control-Max-Age", "86400");
            //与“Access-Control-Allow-Headers”相反，表示不支持的头信息字段
            httpResponse.setHeader("Access-Control-Expose-Headers", "accesstoken");
            httpResponse.setHeader("Access-Control-Request-Headers", "accesstoken");
            //告诉浏览器上缓冲存储的页，立即过期还有多少时间
            httpResponse.setHeader("Expires", "-1");
            //告诉浏览器当前页面不进行缓存，每次访问的时间必须从服务器上读取最新的数据
            httpResponse.setHeader("Cache-Control", "no-cache");
            httpResponse.setHeader("pragma", "no-cache");
             */
        }
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
