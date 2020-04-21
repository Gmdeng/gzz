package com.gzz.shiro.action;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class IndexAction {
    @Value("${spring.data.user.age}")
    private String age;

    @RequestMapping("/helloWord")
    public String index(String name) {
        return "Hello Ricky age:" + age + " name:" + name;
    }

}
