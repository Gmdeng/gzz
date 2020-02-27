package com.gzz.console.core.annotation;

import java.lang.annotation.*;

/**
 * 重复提交（本地锁）的注解
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {

    String key() default "";

    /**
     * 过期时间 TODO 由于用的 guava 暂时就忽略这属性吧 集成 redis 需要用到
     *
     */
    int expire() default 5;
}