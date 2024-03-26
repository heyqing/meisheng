package com.heyqing.shop_back.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName:OrderEntity
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
@TableName(value = "order_table")
@Data
public class OrderEntity {
    /**
     * 订单id
     */
    @TableId(value = "order_id")
    private Long orderId;
    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;
    /**
     * 支付金额
     */
    @TableField(value = "total_price")
    private BigDecimal totalPrice;
    /**
     * 收货人
     */
    @TableField(value = "consignee")
    private String consignee;
    /**
     * 联系方式
     */
    @TableField(value = "tell_phone")
    private String tellPhone;
    /**
     * 收货地址
     */
    @TableField(value = "address")
    private String address;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private Date payTime;
    /**
     * 支付状态
     * 0 全部
     * 1 已支付
     * 2 运输中
     * 3 退款
     */
    @TableField(value = "state")
    private Integer state;
}
