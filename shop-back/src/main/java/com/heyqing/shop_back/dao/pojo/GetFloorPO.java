package com.heyqing.shop_back.dao.pojo;

import com.heyqing.shop_back.dao.pojo.enums.GoodsType;
import lombok.Data;

/**
 * ClassName:GetFloorPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/1/26
 * @Author:Heyqing
 */
@Data
public class GetFloorPO {

    /**
     * 楼层
     */
    private String floorType = GoodsType.FLOOR.getType();

    /**
     * 一次请求返回数量
     */
    private Integer responseNum;

    /**
     * 查询次数
     */
    private Integer selectNum;
}
