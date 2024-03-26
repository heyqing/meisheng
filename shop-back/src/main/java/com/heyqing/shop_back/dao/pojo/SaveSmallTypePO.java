package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

/**
 * ClassName:SaveSmallTypePO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/13
 * @Author:Heyqing
 */
@Data
public class SaveSmallTypePO {
    /**
     * 商品小类id
     */
    private Long categoryId;
    /**
     * 商品小类名称
     */
    private String categoryName;
    /**
     * 商品小类title
     */
    private String categoryTitle;
    /**
     * 商品小类图片
     */
    private String categoryAvatar;
    /**
     * 商品小类描述
     */
    private String remark;
    /**
     * 商品大类id
     */
    private Long sortId;
    /**
     * 商品大类
     */
    private SaveBigTypePO bigType;
}
