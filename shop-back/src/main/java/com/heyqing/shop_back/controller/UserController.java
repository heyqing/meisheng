package com.heyqing.shop_back.controller;

import com.heyqing.shop_back.dao.pojo.LoginPO;
import com.heyqing.shop_back.service.UserService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:UserController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/2/2
 * @Author:Heyqing
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param loginPO
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginPO loginPO) {
        return userService.login(loginPO);
    }

}
