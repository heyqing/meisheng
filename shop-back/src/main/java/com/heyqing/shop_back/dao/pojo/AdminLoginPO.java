package com.heyqing.shop_back.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * ClassName:AdminLoginPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
@Data
public class AdminLoginPO {

    /**
     * 管理员名称
     */
    private String userName;
    /**
     * 管理员密码
     */
    private String password;
}
