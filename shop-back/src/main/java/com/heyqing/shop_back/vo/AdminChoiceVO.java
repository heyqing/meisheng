package com.heyqing.shop_back.vo;

import lombok.Data;

import java.util.Date;

/**
 * ClassName:AdminChoiceVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/3/10
 * @Author:Heyqing
 */
@Data
public class AdminChoiceVO {

    /**
     * 定制id
     */
    private String choiceId;

    /**
     * 定制店面类型
     */
    private String choiceShop;

    /**
     * 定制位置
     */
    private String choiceCurrentRegion;

    /**
     * 是否为连锁店
     */
    private Integer choiceChainStore;

    /**
     * 定制商品类型
     */
    private String choiceGoods;

    /**
     * 定制电话
     */
    private String choicePhone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
