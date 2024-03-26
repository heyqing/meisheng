package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:GetCategoryEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/1/29
 * @Author:Heyqing
 */
@TableName(value = "category_table")
@Data
public class CategoryEntity {
    /**
     * 商品大类id
     */
    @TableId(value = "category_id")
    private Long categoryId;
    /**
     * 分类id
     */
    @TableField(value = "sort_id")
    private String sortId;
    /**
     * 商品大类图片
     */
    @TableField(value = "category_avatar")
    private String categoryAvatar;
    /**
     * 商品大类名称
     */
    @TableField(value = "category_name")
    private String categoryName;
    /**
     * 商品大类标签
     */
    @TableField(value = "category_title")
    private String categoryTitle;
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
    /**
     * 商品小类描述
     */
    @TableField(value = "remark")
    private String remark;

}
