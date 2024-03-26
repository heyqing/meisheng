package com.heyqing.shop_back.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:UserMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/2/2
 * @Author:Heyqing
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 用户查询
     *
     * @param page
     * @param query
     * @return
     */
    Page<UserEntity> userList(Page<UserEntity> page, String query);
}
