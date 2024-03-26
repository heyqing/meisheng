package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:ChoiceEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/3/1
 * @Author:Heyqing
 */
@Data
@TableName(value = "choice_table")
public class ChoiceEntity {

    /**
     * 定制id
     */
    @TableId(value = "choice_id")
    private Long choiceId;
    /**
     * 定制店类型
     */
    @TableField(value = "choice_shop")
    private String choiceShop;
    /**
     * 定制地址
     */
    @TableField(value = "choice_current_region")
    private String choiceCurrentRegion;
    /**
     * 是否为连锁店
     */
    @TableField(value = "choice_chain_store")
    private Integer choiceChainStore;
    /**
     * 定制商品
     */
    @TableField(value = "choice_goods")
    private String choiceGoods;
    /**
     * 客户电话
     */
    @TableField(value = "choice_Phone")
    private String choicePhone;
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
