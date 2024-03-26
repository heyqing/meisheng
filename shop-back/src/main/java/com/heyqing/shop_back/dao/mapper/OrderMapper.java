package com.heyqing.shop_back.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:OrderMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
    /**
     * 获取页面元素
     *
     * @param page
     * @param query
     * @return
     */
    Page<OrderEntity> getPageList(Page<OrderEntity> page, String query);
}
