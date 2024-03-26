package com.heyqing.shop_back.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heyqing.shop_back.dao.entity.SearchEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:SearchMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
@Mapper
public interface SearchMapper extends BaseMapper<SearchEntity> {
    /**
     * 搜索商品
     *
     * @param q
     * @return
     */
    List<SearchEntity> selectListByQ(String q);
}
