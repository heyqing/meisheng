package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ClassName:Goods
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
@Data
public class Goods {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsAvatar;
}
