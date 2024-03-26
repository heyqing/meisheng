package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AdminLoginPO;
import com.heyqing.shop_back.service.admin.AdminService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:AdminController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
@RestController
@RequestMapping
public class AdminController {

    @Autowired
    private AdminService adminService;
    @PostMapping("adminLogin")
    public Result adminLogin(@RequestBody AdminLoginPO adminLoginPO){
        return adminService.login(adminLoginPO);
    }
}
