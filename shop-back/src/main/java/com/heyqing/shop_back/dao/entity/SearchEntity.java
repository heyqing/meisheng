package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName:SearchEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
@TableName(value = "goods_table")
@Data
public class SearchEntity {

    /**
     * 商品id
     */
    @TableId(value = "goods_id")
    private Long goodsId;
    /**
     * 商品类型
     */
    @TableField(value = "goods_type")
    private String goodsType;
    /**
     * 商品图片
     */
    @TableField(value = "goods_avatar")
    private String goodsAvatar;
    /**
     * 商品视频
     */
    @TableField(value = "goods_video")
    private String goodsVideo;
    /**
     * 商品价格
     */
    @TableField(value = "goods_price")
    private BigDecimal goodsPrice;
    /**
     * 商品名称
     */
    @TableField(value = "goods_name")
    private String goodsName;
    /**
     * 商品标题
     */
    @TableField(value = "goods_title")
    private String goodsTitle;
    /**
     * 商品描述
     */
    @TableField(value = "goods_desc")
    private String goodsDesc;
    /**
     * 商品参数
     */
    @TableField(value = "goods_param")
    private String goodsParam;
    /**
     * 商品详情
     */
    @TableField(value = "goods_detail")
    private String goodsDetail;
    /**
     * 楼层位置
     */
    @TableField(value = "level")
    private Integer level;
    /**
     * 分类id
     */
    @TableField(value = "sort_id")
    private Long sortId;
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

