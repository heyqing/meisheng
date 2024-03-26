package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

/**
 * ClassName:AddAdminPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/7
 * @Author:Heyqing
 */
@Data
public class AuthAdminPO {

    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
