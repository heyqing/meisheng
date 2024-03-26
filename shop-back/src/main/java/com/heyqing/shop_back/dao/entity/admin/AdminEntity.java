package com.heyqing.shop_back.dao.entity.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:AdminEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
@Data
@TableName(value = "admin_table")
public class AdminEntity {

    /**
     * 管理员id
     */
    @TableId(value = "admin_id")
    private Long adminId;
    /**
     * 管理员名称
     */
    @TableField(value = "admin_name")
    private String adminName;
    /**
     * 管理员密码
     */
    @TableField(value = "admin_password")
    private String adminPassword;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

}
