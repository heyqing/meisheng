package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

/**
 * ClassName:AddAdminPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/14
 * @Author:Heyqing
 */
@Data
public class AddAdminPO {
    /**
     * 管理员账户名称
     */
    private String adminName;
    /**
     * 管理员账户密码
     */
    private String adminPassword;
}
