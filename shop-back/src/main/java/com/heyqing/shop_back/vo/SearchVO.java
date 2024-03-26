package com.heyqing.shop_back.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ClassName:SearchVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
@Data
public class SearchVO {

    /**
     * 商品id
     */
    private String  goodsId;
    /**
     * 商品图片
     */
    private String goodsAvatar;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品标题
     */
    private String goodsTitle;
    /**
     * 商品描述
     */
    private String goodsDesc;

}
