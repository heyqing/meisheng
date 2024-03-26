package com.heyqing.shop_back.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.SceneEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:SceneMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/2/28
 * @Author:Heyqing
 */
@Mapper
public interface SceneMapper extends BaseMapper<SceneEntity> {
    Page<SceneEntity> sceneList(Page<SceneEntity> page, String query);
}
