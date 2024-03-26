package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

/**
 * ClassName:ModifyPasswordPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/5
 * @Author:Heyqing
 */
@Data
public class ModifyPasswordPO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 管理员名称
     */
    private String userName;
    /**
     * 管理员密码
     */
    private String password;
    /**
     * 管理员新密码
     */
    private String newPassword;
}
