package com.heyqing.shop_back.vo;

import lombok.Data;

import java.util.Date;

/**
 * ClassName:GetSortVo
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/1/26
 * @Author:Heyqing
 */
@Data
public class GetSortVO {

    /**
     * 分类iid
     */
    private String  sortId;

    /**
     * 分类名称
     */
    private String sortName;

    /**
     * 分类图片
     */
    private String sortAvatar;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 商品描述
     */
    private String remark;
}
