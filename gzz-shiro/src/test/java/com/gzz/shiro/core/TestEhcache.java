package com.gzz.shiro.core;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.junit.Test;

public class TestEhcache {

    /**
     * 通用的读写使用CacheManager
     */
    @Test
    public void testCacheManager(){
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();

        Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);

        Cache<Integer, String> myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, String.class, ResourcePoolsBuilder.heap(10)).build());

        for (int i=0;i<=20;i++){
            //写
            myCache.put(i, "@"+i);
            //读
            String value = myCache.get(i);
            System.out.println("get at "+i+":"+value);
        }

        cacheManager.removeCache("preConfigured");
        cacheManager.close();
    }

    /**
     * 读写新泛型方法UserManagedCache
     */
    @Test
    public void testUserManagedCache(){
        UserManagedCache<Integer, String> userManagedCache =
                UserManagedCacheBuilder.newUserManagedCacheBuilder(Integer.class, String.class)
                        .build(false);
        userManagedCache.init();

        for (int i=0;i<=20;i++){
            //写
            userManagedCache.put(i, "#"+i);
            //读
            String value = userManagedCache.get(i);
            System.out.println("get at "+i+":"+value);
        }

        userManagedCache.close();
    }
}
