package com.heyqing.shop_back.service;

import com.heyqing.shop_back.dao.pojo.GetFloorPO;
import com.heyqing.shop_back.dao.pojo.GetSwiperPO;
import com.heyqing.shop_back.vo.GetFloorVO;
import com.heyqing.shop_back.vo.Result;

/**
 * ClassName:GoodsService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/1/25
 * @Author:Heyqing
 */
public interface GoodsService {

    /**
     * 获取轮播图数据
     *
     * @return
     */
    Result getSwiper();

    /**
     * 获取分类数据
     *
     * @return
     */
    Result getSort();

    /**
     * 获取楼层数据
     *
     * @param getFloorPO
     * @return
     */
    Result getFloor(GetFloorPO getFloorPO);

    /**
     * 获取商品详情
     *
     * @param goodsId
     * @return
     */
    Result getGoodsDetail(Long goodsId);

    /**
     * 获取分类商品
     *
     * @param categoryId
     * @return
     */
    Result getCategoryGoods(Long categoryId);

    /**
     * 商品搜索
     *
     * @param q
     * @return
     */
    Result search(String q);
}
