package com.heyqing.shop_back.dao.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.admin.AdminEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:AdminMapper
 * Package:com.heyqing.shop_back.dao.mapper
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
@Mapper
public interface AdminMapper extends BaseMapper<AdminEntity> {
    Page<AdminEntity> adminList(Page<AdminEntity> page, String query);
}
