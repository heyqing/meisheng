package com.heyqing.shop_back.controller;

import com.heyqing.shop_back.dao.pojo.GetFloorPO;
import com.heyqing.shop_back.service.GoodsService;
import com.heyqing.shop_back.vo.GetFloorVO;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:GoodsController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/1/25
 * @Author:Heyqing
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 获取轮播图数据
     *
     * @return
     */
    @GetMapping("swiperdata")
    public Result getSwiper() {
        return goodsService.getSwiper();
    }

    /**
     * 获取分类数据
     *
     * @return
     */
    @GetMapping(value = "sort")
    public Result getSort() {
        return goodsService.getSort();
    }

    /**
     * 获取楼层数据
     *
     * @return
     */
    @PostMapping("floor")
    public Result getFloor(@RequestBody GetFloorPO getFloorPO) {
        return goodsService.getFloor(getFloorPO);
    }

    /**
     * 获取商品详情
     *
     * @param goodsId
     * @return
     */
    @GetMapping("detail/{goodsId}")
    public Result getGoodsDetail(@PathVariable("goodsId") Long goodsId) {
        return goodsService.getGoodsDetail(goodsId);
    }

    /**
     * 获取分类商品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("categories/{categoryId}")
    public Result getCategoryGoods(@PathVariable("categoryId") Long categoryId) {
        return goodsService.getCategoryGoods(categoryId);
    }

    /**
     * 商品搜索
     *
     * @param q
     * @return
     */
    @GetMapping("search/{q}")
    public Result goodsSearch(@PathVariable("q") String q) {
        return goodsService.search(q);
    }
}
