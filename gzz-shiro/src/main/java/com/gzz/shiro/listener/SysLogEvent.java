package com.gzz.shiro.listener;

import org.springframework.context.ApplicationEvent;

/***
 * ApplicationEvent&Listener完成业务解耦
 *  系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog source) {
        super(source);
    }
}
