package com.heyqing.shop_back.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.ChoiceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:ChoiceMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/3/1
 * @Author:Heyqing
 */
@Mapper
public interface ChoiceMapper extends BaseMapper<ChoiceEntity> {
    /**
     * 查询页面
     *
     * @param page
     * @param query
     * @return
     */
    Page<ChoiceEntity> goodsList(Page<ChoiceEntity> page, String query);
}
