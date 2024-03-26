package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:SceneEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/2/28
 * @Author:Heyqing
 */
@TableName(value = "scene_table")
@Data
public class SceneEntity {

    /**
     * 场景id
     */
    @TableId(value = "scene_id")
    private Long sceneId;
    /**
     * 场景名称
     */
    @TableField(value = "scene_name")
    private String sceneName;
    /**
     * 场景描述
     */
    @TableField(value = "scene_desc")
    private String sceneDesc;
    /**
     * 场景商品
     */
    @TableField(value = "scene_goods")
    private String sceneGoods;
    /**
     * 场景类型
     */
    @TableField(value = "scene_type")
    private String sceneType;
    /**
     * 场景图片
     */
    @TableField(value = "scene_avatar")
    private String sceneAvatar;
    /**
     * 场景详情
     */
    @TableField(value = "scene_detail")
    private String sceneDetail;
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
