package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

/**
 * ClassName:AdminPageListPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/3/7
 * @Author:Heyqing
 */
@Data
public class AdminPageListPO {

    /**
     * 查询关键字
     */
    private String query;
    /**
     * 查询页数
     */
    private Integer pageNum;
    /**
     * 查询数量
     */
    private Integer pageSize;
}
