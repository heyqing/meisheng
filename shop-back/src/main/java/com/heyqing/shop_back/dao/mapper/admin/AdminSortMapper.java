package com.heyqing.shop_back.dao.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.SortEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:AdminSortMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/2/8
 * @Author:Heyqing
 */
@Mapper
public interface  AdminSortMapper extends BaseMapper<SortEntity> {
    /**
     * 商品列表查询
     *
     * @param page
     * @param query
     * @return
     */
    Page<SortEntity> goodsList(Page<SortEntity> page, String query);
}
