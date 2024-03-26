package com.heyqing.shop_back.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:GetCategoryMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/1/29
 * @Author:Heyqing
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {
    /**
     * 查询小类
     *
     * @param page
     * @param query
     * @return
     */
    Page<CategoryEntity> goodsList(Page<CategoryEntity> page, String query);

    /**
     * 查询所有title
     *
     * @return
     */
    List<String> selectAllTitle();

}
