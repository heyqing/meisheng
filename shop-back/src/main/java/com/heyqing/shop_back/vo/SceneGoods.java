package com.heyqing.shop_back.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ClassName:SceneGoods
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/3/2
 * @Author:Heyqing
 */
@Data
public class SceneGoods {
    private String goodsId;
    private String goodsName;
    private BigDecimal goodsPrice;
}
