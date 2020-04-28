package com.gzz.shiro.listener;

import org.apache.catalina.security.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * 异步日志切面
 */
public class SysLogAspect {
    private SysLog sysLog = new SysLog();
    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;

    /***
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(com.xd.pre.log.SysLog)")
    public void sysLogAspect() {

    }

    /***
     * 拦截控制层的操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {

        // 开始时间
        long beginTime = Instant.now().toEpochMilli();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        PreUser securityUser = SecurityUtil.getUser();
//        sysLog.setUserName(securityUser.getUsername());
       // sysLog.setActionUrl(URLUtil.getPath(request.getRequestURI()));
        sysLog.setStartTime(LocalDateTime.now());
       // sysLog.setRequestIp(ServletUtil.getClientIP(request));
        sysLog.setRequestMethod(request.getMethod());
        sysLog.setUa(request.getHeader("user-agent"));
        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        //获取执行的方法名
        sysLog.setActionMethod(joinPoint.getSignature().getName());
        // 类名
        sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
        sysLog.setActionMethod(joinPoint.getSignature().getName());
        sysLog.setFinishTime(LocalDateTime.now());
        // 参数
        sysLog.setParams(Arrays.toString(args));
        //sysLog.setDescription(LogUtil.getControllerMethodDescription(joinPoint));
        long endTime = Instant.now().toEpochMilli();
        sysLog.setConsumingTime(endTime - beginTime);
    }

    /**
     * 返回通知
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
//        R r = Convert.convert(R.class, ret);
//        if (r.getCode() == 200){
//            // 正常返回
//            sysLog.setType(1);
//        } else {
//            sysLog.setType(2);
//            sysLog.setExDetail(r.getMsg());
//        }
        // 发布事件
        applicationContext.publishEvent(new SysLogEvent(sysLog));
    }

    /**
     * 异常通知
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()",throwing = "e")
    public void doAfterThrowable(Throwable e){
        // 异常
        sysLog.setType(2);
        // 异常对象
        sysLog.setExDetail(LogUtil.getStackTrace(e));
        // 异常信息
        sysLog.setExDesc(e.getMessage());
        // 发布事件
        applicationContext.publishEvent(new SysLogEvent(sysLog));
    }
}
