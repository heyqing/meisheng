package com.heyqing.shop_back.dao.pojo;

import com.heyqing.shop_back.dao.pojo.enums.GoodsType;
import lombok.Data;

/**
 * ClassName:GetSwiperPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/1/25
 * @Author:Heyqing
 */
@Data
public class GetSwiperPO {

    /**
     * 轮播图
     */
    private String goodsType = GoodsType.SWIPER.getType();

}
