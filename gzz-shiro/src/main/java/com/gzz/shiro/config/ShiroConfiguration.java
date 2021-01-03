package com.gzz.shiro.config;

import com.google.common.collect.Maps;
import com.gzz.shiro.core.shiro.*;
import com.gzz.shiro.core.shiro.filter.KickoutSessionFilter;
import com.gzz.shiro.core.shiro.filter.SigninFilter;
import com.gzz.shiro.core.shiro.filter.SignoutFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.Map;

/**
 * shiro 配置
 */
@Configuration
@AutoConfigureAfter(RedisConfiguration.class)
public class ShiroConfiguration {
    private static final String HASH_ALGORITHM = "MD5"; // 散列算法:这里使用MD5算法;
    private static final int HASH_INTERATIONS = 21; // 散列的次数，比如散列两次，相当于 md5(md5(""))
    private final Logger logger = LogManager.getLogger(LogManager.FACTORY_PROPERTY_NAME);
    public ShiroConfiguration() {
        logger.info("Shiro-Config 初始化。。。");
    }
    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, KickoutSessionFilter kickoutSessionFilter) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setLoginUrl("/login");                //登录地址
        shiroFilter.setSuccessUrl("/index");              //登录成功地址
        shiroFilter.setUnauthorizedUrl("/error");         //错误页面，认证不通过跳转
        shiroFilter.setSecurityManager(securityManager);
        //设置过滤器
        Map<String, Filter> filters = Maps.newHashMap();
        filters.put("authc", new SigninFilter());
        filters.put("kickout", kickoutSessionFilter);
        filters.put("signout", new SignoutFilter());
        shiroFilter.setFilters(filters);

        // 要拦截地址。必须是有序的
        Map<String, String> urlMap = Maps.newLinkedHashMap();
        urlMap.put("/auth/login", "authc");
        urlMap.put("/auth/logout", "signout");
        //urlMap.put("/**", "kickout,authc");
        urlMap.put("/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(urlMap);
        return shiroFilter;
    }

    /**
     * Shiro安全管理器 securityManager
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager(SessionManager sessionManager, CustomAuthorizingRealm shiroRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);  //认证管理器
        defaultWebSecurityManager.setCacheManager(cacheManager()); //缓存管理器
        defaultWebSecurityManager.setSessionManager(sessionManager); // sessionManager管理器
        return defaultWebSecurityManager;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * Shiro 生命周期处理器, 保证实现
     *
     * @return
     */
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 缓存管理器
     *
     * @return
     */
    private CacheManager cacheManager() {
        return new RedisCacheManager();
    }

    @Bean
    public RedisSessionDao redisSessionDao(RedisTemplate<String,Object> template){
        RedisSessionDao redisSessionDao = new RedisSessionDao();
        redisSessionDao.setRedisTemplate(template);
        return redisSessionDao;
    }
    /**
     * SESSION管理器
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager(RedisSessionDao redisSessionDao) {
        RedisSessionManager redisSessionManager = new RedisSessionManager();
        redisSessionManager.setSessionDAO(redisSessionDao);  //session存储的实现
        redisSessionManager.setSessionIdUrlRewritingEnabled(false); //去掉URL中的JSESSIONID
        redisSessionManager.setGlobalSessionTimeout(360000L); //设置全局会话超时时间(session的失效时长)，单位毫秒(单位：毫秒)，默认为30分钟
        redisSessionManager.setDeleteInvalidSessions(true); //是否在会话过期后会调用SessionDAO的delete方法删除会话 默认true
        redisSessionManager.setSessionValidationInterval(1800000); // 相隔多久检查一次session的有效性 定时清理失效会话, 清理用户直接关闭浏览器造成的孤立会话
        redisSessionManager.setSessionValidationSchedulerEnabled(true);//定时检查失效的session
        redisSessionManager.setSessionIdCookieEnabled(true);
        redisSessionManager.setSessionIdCookie(sessionIdCookie());
        //redisSessionManager.setSessionValidationScheduler(); //会话验证调度器
        return redisSessionManager;
    }

    private SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("shiro.sesssion");

        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);

        return simpleCookie;
    }

    /**
     * 自定义密码比较器(增加偿试次数限制)
     *
     * @return
     */
    @Bean
    public RetryLimitCredentialsMatcher retryLimitCredentialsMatcher() {
        RetryLimitCredentialsMatcher credentialsMatcher = new RetryLimitCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(HASH_ALGORITHM);
        credentialsMatcher.setHashIterations(HASH_INTERATIONS);
        return credentialsMatcher;
    }

    /**
     * 这是我自己的realm 我自定义了一个密码解析器
     *
     * @return
     */
    @Bean
    public CustomAuthorizingRealm shiroRealm(RetryLimitCredentialsMatcher retryLimitCredentialsMatcher) {
        // 配置 Realm，需自己实现
        CustomAuthorizingRealm realm = new CustomAuthorizingRealm();
        realm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthenticationCacheName("authenticationCache");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCacheName("authorizationCache");
        // 配置自定义密码比较器
        realm.setCredentialsMatcher(retryLimitCredentialsMatcher);
        return realm;
    }

    /**
     * 并发登录人数控制过滤器(同时在线人数据限制)
     *
     * @return
     */
    @Bean
    public KickoutSessionFilter kickoutSessionFilter(SessionManager sessionManager) {
        KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
        kickoutSessionFilter.setMaxSession(2);
        kickoutSessionFilter.setSessionManager(sessionManager);
        return kickoutSessionFilter;
    }

}
