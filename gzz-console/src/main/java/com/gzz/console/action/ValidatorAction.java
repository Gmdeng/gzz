package com.gzz.console.action;


import com.gzz.console.core.CustomException;
import com.gzz.console.core.ValidGroup;
import com.gzz.console.core.annotation.CheckDateTime;
import com.gzz.console.vo.BookVo;
import com.gzz.console.vo.UserVo;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Enumeration;


@RestController
public class ValidatorAction {

    @PostMapping("/testUser")
    public Object testUser(UserVo userVo, HttpServletRequest request){
       Enumeration<String> evn = request.getHeaderNames();
        System.out.print(evn);
        userVo.setPasswd(userVo.getName() + userVo.getPasswd());
        return userVo;
    }

    @GetMapping("/insert")
    public String insert(@Validated(value = ValidGroup.Default.class) BookVo book) {
        return "insert";
    }


    @GetMapping("/update")
    public String update(@Validated(value = {ValidGroup.Default.class, ValidGroup.Update.class}) BookVo book) {
        return "update";
    }


    @GetMapping("/test2")
    public String test2(@NotBlank(message = "name 不能为空") @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name) {
        return "success==="+name;
    }

    @GetMapping("/test3")
    public String test3(@Validated BookVo book) {
        return "success";
    }

    @GetMapping("/testDateTime")
    public String test(@CheckDateTime(message = "您输入的格式错误，正确的格式为：{format}", format = "yyyy-MM-dd HH:mm") String date) {
        return "success";
    }

    /**
     * 全局异常处理 测试
     * @param num
     * @return
     */
    @GetMapping("/testGlobalException")
    public String testGlobalException(Integer num) {
        // TODO 演示需要，实际上参数是否为空通过 @RequestParam(required = true)  就可以控制
        if (num == null) {
            throw new CustomException(400, "num不能为空");
        }
        int i = 10 / num;
        return "result:" + i;
    }
}
