package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:UserEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/2/2
 * @Author:Heyqing
 */
@TableName(value = "user_table")
@Data
public class UserEntity {
    /**
     * userId
     */
    @TableId(value = "user_id")
    private Long userId;
    /**
     * openid用户唯一标识
     */
    @TableField(value = "openid")
    private String openid;
    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;
    /**
     * 用户头像地址
     */
    @TableField(value = "user_avatar_url")
    private String userAvatarUrl;
    /**
     * 用户注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 用户最后登录时间
     */
    @TableField(value = "last_time")
    private Date lastTime;

}
