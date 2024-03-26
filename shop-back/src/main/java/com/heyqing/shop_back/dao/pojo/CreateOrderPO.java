package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName:CreateOrderPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
@Data
public class CreateOrderPO {

    /**
     * 支付金额
     */
    private BigDecimal totalPrice;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 联系方式
     */
    private String tellPhone;
    /**
     * 商品
     */
    private List<Goods> goods;
}
