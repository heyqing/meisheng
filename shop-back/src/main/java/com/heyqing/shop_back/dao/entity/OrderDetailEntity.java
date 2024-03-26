package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * ClassName:OrderDetailEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
@TableName(value = "order_detail_table")
@Data
public class OrderDetailEntity {
    /**
     * 订单详情id
     */
    @TableId(value = "order_detail_id")
    private Long orderDetailId;
    /**
     * 订单id
     */
    @TableField(value = "mid")
    private Long mid;
    /**
     * 商品id
     */
    @TableField(value = "goods_id")
    private Long goodsId;
    /**
     * 商品数量
     */
    @TableField(value = "goods_num")
    private Integer goodsNum;
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
     * 商品图片
     */
    @TableField(value = "goods_avatar")
    private String goodsAvatar;
}
