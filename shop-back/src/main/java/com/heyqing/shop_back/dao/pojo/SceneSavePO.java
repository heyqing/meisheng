package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

import java.util.List;

/**
 * ClassName:SceneSavePO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/3/1
 * @Author:Heyqing
 */
@Data
public class SceneSavePO {
    /**
     * 定制地址
     */
    private List<String> currentRegion;
    /**
     * 是否为连锁店
     */
    private Boolean isSwitch;
    /**
     * 定制店类型
     */
    private String shopType;
    /**
     * 定制商品类型
     */
    private String goodsType;
    /**
     * 客户电话
     */
    private String phone;
}
