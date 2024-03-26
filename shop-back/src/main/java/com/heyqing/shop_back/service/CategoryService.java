package com.heyqing.shop_back.service;

import com.heyqing.shop_back.dao.pojo.GetCategoryPO;
import com.heyqing.shop_back.vo.Result;

/**
 * ClassName:CategoryService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/1/29
 * @Author:Heyqing
 */
public interface CategoryService {
    /**
     * 获取商品大类
     *
     * @param getCategoryPO
     * @return
     */
    Result getCategory(GetCategoryPO getCategoryPO);
}
