package com.heyqing.shop_back.service;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.LoginPO;
import com.heyqing.shop_back.vo.Result;

/**
 * ClassName:UserService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/2/2
 * @Author:Heyqing
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param loginPO
     * @return
     */
    Result login(LoginPO loginPO);

    /**
     * 查询用户
     *
     * @param adminPageListPO
     * @return
     */
    Result getUserList(AdminPageListPO adminPageListPO);

}
