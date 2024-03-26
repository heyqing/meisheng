package com.heyqing.shop_back.dao.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName:GoodsType
 * Package:com.heyqing.shop_back.dao.pojo.enums
 * Description:
 *
 * @Date:2024/1/25
 * @Author:Heyqing
 */
@AllArgsConstructor
@Getter
public enum GoodsType {

    /**
     * 商品-轮播图
     */
    SWIPER("swiper"),


    /**
     * 商品-楼层
     */
    FLOOR("floor");
    private String type;

}
