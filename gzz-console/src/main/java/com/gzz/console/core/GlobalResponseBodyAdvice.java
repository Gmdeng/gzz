package com.gzz.console.core;

import com.gzz.console.core.annotation.LocalLock;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

public class GlobalResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    // public boolean supports(MethodParameter returnType, Class converterType) {
    public boolean supports(MethodParameter returnType, Class converterType) {
        return !returnType.hasMethodAnnotation(LocalLock.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 获取request为特殊需要的时候处理使用
        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        if (selectedConverterType == MappingJackson2HttpMessageConverter.class && selectedContentType.equals(MediaType.APPLICATION_JSON) || selectedContentType.equals(MediaType.APPLICATION_JSON_UTF8)) {
            if( body == null){
                return null;  //
            }else if( body instanceof LocalLock) {
                //
            }else {
                //
            }

        }
        return body;
    }
}
