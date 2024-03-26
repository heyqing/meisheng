package com.heyqing.shop_back.vo;

import lombok.Data;

import java.util.Date;

/**
 * ClassName:GetCategoryVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/1/29
 * @Author:Heyqing
 */
@Data
public class GetCategoryVO {

    /**
     * 商品小类id
     */
    private String categoryId;
    /**
     * 商品小类图片
     */
    private String categoryAvatar;
    /**
     * 商品小类名称
     */
    private String categoryName;
    /**
     * 商品小类标签
     */
    private String categoryTitle;
    /**
     * 商品小类-大类名称
     */
    private String sortName;
    /**
     * 商品大类实体
     */
    private GetSortVO bigType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 商品小类描述
     */
    private String remark;
}
