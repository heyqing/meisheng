package com.heyqing.shop_back.vo;

import lombok.Data;

import java.util.List;

/**
 * ClassName:GetCategoryListVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/1/29
 * @Author:Heyqing
 */
@Data
public class GetCategoryListVO {
    /**
     * 商品大类标签
     */
    private String categoryTitle;
    /**
     * 商品大类返回实体
     */
    private List<GetCategoryVO> getCategoryVOList;
}
