package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:ChoiceAvatarEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/3/1
 * @Author:Heyqing
 */
@Data
@TableName(value = "choice_avatar_table")
public class ChoiceAvatarEntity {
    /**
     * 定制图片id
     */
    @TableId(value = "choice_avatar_id")
    private Long choiceAvatarId;
    /**
     * 定制图片
     */
    @TableField(value = "choice_avatar")
    private String choiceAvatar;
    /**
     * 定制详情
     */
    @TableField(value = "choice_photo")
    private String choicePhoto;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}
