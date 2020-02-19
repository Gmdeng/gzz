package com.gzz.console.action;
/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MainService
 * Author:   G-m
 * Date:     2018/6/26 15:47
 * Description: Go TODO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
import com.gzz.console.service.UserService;
import com.gzz.console.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br>
 * 〈Go TODO〉
 *
 * @author G-m
 * @create 2018/6/26
 * @since 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    public UserVo info(){
        return userService.getUser();
    }
}
