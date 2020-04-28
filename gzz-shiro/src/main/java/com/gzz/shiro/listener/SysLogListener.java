package com.gzz.shiro.listener;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * ApplicationEvent&Listener完成业务解耦
 * 注解形式的监听 异步监听日志事件
 */
public class SysLogListener {
    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        // SysLog sysLog = (SysLog) event.getSource();
        // 保存日志
        // sysLogService.save(sysLog);
    }
}
