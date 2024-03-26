package com.heyqing.shop_back.vo;

import lombok.Data;

import java.util.Date;

/**
 * ClassName:AdminVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/2/13
 * @Author:Heyqing
 */
@Data
public class AdminVO {
    /**
     * 管理员id
     */
    private String adminId;
    /**
     * 管理员名称
     */
    private String adminName;
    /**
     * 管理员密码
     */
    private String adminPassword;
    /**
     * 创建时间
     */
    private Date createTime;

}
