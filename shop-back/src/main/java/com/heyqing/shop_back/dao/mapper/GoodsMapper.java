package com.heyqing.shop_back.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:GoodsMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/3/1
 * @Author:Heyqing
 */
@Mapper
public interface GoodsMapper extends BaseMapper<GoodsEntity> {
    /**
     * admin-查询所有商品
     *
     * @param page
     * @param query
     * @return
     */
    Page<GoodsEntity> goodsList(Page<GoodsEntity> page, String query);
}
