package com.gzz.shiro.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * cf
 */
public class JsonUtil {

    /**
     * JAVA对象转JSON字符串
     * @param obj
     * @return
     */
    public static String toJSON(Object obj){
        return JSONObject.toJSONString(obj);
    }

    /**
     * JSON字符串转JAVA简单对象
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T paseBean(String jsonString, Class<T> clazz){
        return JSONObject.parseObject(jsonString, clazz);
    }

    public static <T> T paseBean(String jsonString){
        return (T) JSONObject.parseObject(jsonString);
    }
    public static <T> T paseBean(String jsonString, TypeReference<T> clazz){
        return JSONObject.parseObject(jsonString, clazz);
    }

    public static <T> List<T> parseBeanList(String jsonString, Class<T> clazz){
        return JSONArray.parseArray(jsonString, clazz);
    }

    public static <T> List<T> parseBeanList(String jsonString, TypeReference<List<T>> clazz){
        return  JSON.parseObject(jsonString, clazz);
    }


}
