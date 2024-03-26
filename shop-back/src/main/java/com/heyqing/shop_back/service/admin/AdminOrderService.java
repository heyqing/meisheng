package com.heyqing.shop_back.service.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.UpdateStatusPO;
import com.heyqing.shop_back.vo.Result;

/**
 * ClassName:AdminOrderService
 * Package:com.heyqing.shop_back.service.admin
 * Description:
 *
 * @Date:2024/3/7
 * @Author:Heyqing
 */
public interface AdminOrderService {

    /**
     * 获取页面元素
     *
     * @param adminPageListPO
     * @return
     */
    Result getList(AdminPageListPO adminPageListPO);

    /**
     * 获取订单详情
     *
     * @param orderId
     * @return
     */
    Result getOrderDetail(Long orderId);

    /**
     * 更改订单状态
     *
     * @param updateStatusPO
     * @return
     */
    Result updateStatus(UpdateStatusPO updateStatusPO);

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    Result delete(Long orderId);
}
