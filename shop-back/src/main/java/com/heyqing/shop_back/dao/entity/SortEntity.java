package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * ClassName:GetSortEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/1/26
 * @Author:Heyqing
 */
@TableName(value = "sort_table")
@Data
@Entity
public class SortEntity {

    /**
     * 分类id
     */
    @Id
    @TableId(value = "sort_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sortId;
    /**
     * 分类类型
     */
    @TableField(value = "sort_type")
    private String sortType;
    /**
     * 分类图片
     */
    @TableField(value = "sort_avatar")
    private String sortAvatar;
    /**
     * 分类名称
     */
    @TableField(value = "sort_name")
    private String sortName;
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
     * 商品大类描述
     */
    @TableField(value = "remark")
    private String remark;

}
