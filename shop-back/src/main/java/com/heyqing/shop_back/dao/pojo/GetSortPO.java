package com.heyqing.shop_back.dao.pojo;

import com.heyqing.shop_back.dao.pojo.enums.GoodsType;
import com.heyqing.shop_back.dao.pojo.enums.SortType;
import lombok.Data;

/**
 * ClassName:GetSortPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/1/26
 * @Author:Heyqing
 */
@Data
public class GetSortPO {

    /**
     * 分类
     */
    private String sortType = SortType.SORT.getType();

}
