package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

import java.util.function.Predicate;

/**
 * ClassName:UpdateStatusPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/3/8
 * @Author:Heyqing
 */
@Data
public class UpdateStatusPO {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单状态
     */
    private Integer state;
}
