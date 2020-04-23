package com.gzz.shiro.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching //开启注解
public class RedisConfiguration extends CachingConfigurerSupport {
    private final Logger logger = LogManager.getLogger(LogManager.FACTORY_PROPERTY_NAME);


    public RedisConfiguration() {
        logger.info("Redis-Config 初始化。。。");
    }
    public KeyGenerator keyGenerator() {
        return ((target, method, params) -> {
           StringBuilder sb = new StringBuilder();
           sb.append(target.getClass().getName());
           sb.append(":");
           sb.append(method.getName());
            for (Object obj: params) {
                sb.append(":"+ String.valueOf(obj));
            }
            String rsToUse = String.valueOf(sb);
            logger.info("Generator auto Key -->{}", rsToUse);
            return rsToUse;
        });
    }

    /**
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.create(connectionFactory);
    }

//  @Bean
//  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//      RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//      redisTemplate.setConnectionFactory(factory);
//      return redisTemplate;
//  }

    /**
     * retemplate相关配置
     *
     * @param connectionFactory
     * @return
     */
    @SuppressWarnings("deprecation")
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        // Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);


        //使用StringRedisSerializer来序列化和反序列化redis的key值
        // 设置hash key 和value序列化模式
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        // 值采用json序列化
//        template.setValueSerializer(jackson2JsonRedisSerializer);  //自定义
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);  //自定义
//        template.setDefaultSerializer(jackson2JsonRedisSerializer); //自定义

//        // 3.创建 自定义序列化类
        MyRedisSerializer myRedisSerializer = new MyRedisSerializer();
        // 7.设置 value 的转化格式和 key 的转化格式 默认使用的是JdkSerializationRedisSerializer
        template.setValueSerializer(myRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setDefaultSerializer(myRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    /**
     * 异常处理，当Redis 发生异常时，打印日志，但是程序正常走
     * @return
     */
    @Bean
    public CacheErrorHandler errorHandler(){
       return new CacheErrorHandler(){
            // logger.info("初始化 -> [{}]", "Redis CacheErrorHandler");
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                logger.error("Redis occur handleCacheGetError: key -> [{}]",key, exception);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                logger.error("Redis occur handleCachePutError: key -> [{}]; value -> [{}]",key,value, exception);

            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                logger.error("Redis occur handleCacheEvictError: key -> [{}]",key, exception);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                logger.error("Redis occur handleCacheClearError", exception);
            }
        };
    }
}