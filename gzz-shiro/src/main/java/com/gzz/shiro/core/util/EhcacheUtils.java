package com.gzz.shiro.core.util;

import com.alibaba.fastjson.TypeReference;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Ehcache 工具类
 * @description: TODO
 */

public class EhcacheUtils {

    //初始化Ehcache配置
    private static CacheConfiguration<String, String> usesConfiguredInCacheConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
            ResourcePoolsBuilder.newResourcePoolsBuilder()
                    .heap(300L, EntryUnit.ENTRIES)
                    .offheap(20L, MemoryUnit.MB))
            .withSizeOfMaxObjectGraph(1000L)
            .withSizeOfMaxObjectSize(100L, MemoryUnit.KB)
            .withExpiry(Expirations.timeToLiveExpiration(Duration.of(30, TimeUnit.MINUTES))) //失效时间
            .build();

    //初始化管理器
    private static CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withDefaultSizeOfMaxObjectSize(50L, MemoryUnit.KB)
            .withDefaultSizeOfMaxObjectGraph(2000)
            .withCache("usesConfiguredInCache", usesConfiguredInCacheConfig)
            .build(true);

    //获取cache对象
    private static Cache<String, String> usesConfiguredInCache = cacheManager.getCache("usesConfiguredInCache", String.class, String.class);


    /**
     * @Title: putIntoCache
     * @Description: TODO
     * @param key
     * @param Value
     * void 返回类型
     * @throws
     */
    public static void putIntoCache(String key,Object Value){
        usesConfiguredInCache.put(key, JsonUtil.toJSON(Value));
    }

    /**
     * @Title: getFromCache
     * @Description: 返回java对象
     * @param key
     * @param clazz
     * @return T 返回类型
     * @throws
     */
    public static <T> T getFromCache(String key,Class<T> clazz){
        return JsonUtil.paseBean(usesConfiguredInCache.get(key), clazz);
    }
    /**
     * @Title: getListFromCache
     * @Description: 返回list对象
     * @param key
     * @param clazz
     * @param
     * @return List<T> 返回类型
     * @throws
     */
    public static <T> List<T> getListFromCache(String key,Class<T> clazz){
        return JsonUtil.parseBeanList(usesConfiguredInCache.get(key),clazz);
    }
    public static <T> T getMapFromCache(String key, TypeReference<T> type) {
        return  JsonUtil.paseBean(usesConfiguredInCache.get(key),type);
    }
    /**
     * @Title: getMapFromCache
     * @Description: 返回Map<String, Object>对象
     * @param key
     * @throws
     */
    public static Map<String, Object> getMapFromCache(String key){
        return JsonUtil.paseBean(usesConfiguredInCache.get(key),Map.class);
    }

    /**
     * @Title: clear
     * @Description:清除ehcache缓存数据
     * @return void 返回类型
     * @throws
     */
    public static void clear(){
        usesConfiguredInCache.clear();
    }

    /**
     *
     * @Title: close
     * @Description: 关闭管理器
     * @param
     * @return void 返回类型
     * @throws
     */
    public static void close(){
        cacheManager.close();
    }

    /**
     * @Title: getMapFromCache
     * @Description: 返回Map<String, Object>对象
     * @param key
     * @throws
     */
    public static <T> T getMapObjFromCache(String key){
        return JsonUtil.paseBean(usesConfiguredInCache.get(key));
    }
}
