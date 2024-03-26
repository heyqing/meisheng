package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

/**
 * ClassName:LoginPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/2
 * @Author:Heyqing
 */
@Data
public class LoginPO {

    /**
     * 用户登录凭证
     */
    private String code;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像地址
     */
    private String avatarUrl;

}
