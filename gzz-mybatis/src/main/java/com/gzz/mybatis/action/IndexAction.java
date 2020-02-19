package com.gzz.mybatis.action;

import com.gzz.mybatis.pojo.MiMember;
import com.gzz.mybatis.service.MiMemberService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MiMemberService memberService;

    @RequestMapping("/helloWord")
    public String index(String name){
        return "Hello Ricky age:" + age + " name:" + name;
    }

    @RequestMapping("/getMemberInfo")
    public Object getMember(String memberNo){
        MiMember member = memberService.getMiMember(memberNo);
        return member;
    }
}
