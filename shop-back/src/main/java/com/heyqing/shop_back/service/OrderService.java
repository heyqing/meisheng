package com.heyqing.shop_back.service;

import com.heyqing.shop_back.dao.pojo.CreateOrderPO;
import com.heyqing.shop_back.dao.pojo.PreparePayOrderPO;
import com.heyqing.shop_back.vo.Result;

/**
 * ClassName:OrderService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
public interface OrderService {

    /**
     * 创建订单信息
     *
     * @param token
     * @param createOrderPO
     * @return
     */
    Result createOrder(String token, CreateOrderPO createOrderPO);

    /**
     * 预支付
     *
     * @param token
     * @param preparePayOrderPO
     * @return
     */
    Result preparePayOrder(String token, PreparePayOrderPO preparePayOrderPO);

    /**
     * 订单查询
     *
     * @param type
     * @param page
     * @param pageSize
     * @param token
     * @return
     */
    Result list(Integer type, Integer page, Integer pageSize, String token);

    /**
     * 获取订单详情
     *
     * @param orderId
     * @param token
     * @return
     */
    Result detail(Long orderId,String token);
}
