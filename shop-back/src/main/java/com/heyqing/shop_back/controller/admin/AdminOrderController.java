package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.UpdateStatusPO;
import com.heyqing.shop_back.service.admin.AdminOrderService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:AdminOrderController
 * Package:com.heyqing.shop_back.controller.admin
 * Description:
 *
 * @Date:2024/3/7
 * @Author:Heyqing
 */
@RestController
@RequestMapping("admin/order")
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    /**
     * 获取page页面
     *
     * @param adminPageListPO
     * @return
     */
    @PostMapping("list")
    public Result getList(@RequestBody AdminPageListPO adminPageListPO) {
        return adminOrderService.getList(adminPageListPO);
    }

    /**
     * 获取订单详情
     *
     * @param orderId
     * @return
     */
    @GetMapping("detail/{id}")
    public Result getOrderDetail(@PathVariable("id") Long orderId) {
        return adminOrderService.getOrderDetail(orderId);
    }

    /**
     * 更改订单状态
     *
     * @param updateStatusPO
     * @return
     */
    @PostMapping("updateStatus")
    public Result updateStatus(@RequestBody UpdateStatusPO updateStatusPO) {
        return adminOrderService.updateStatus(updateStatusPO);
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @GetMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long orderId) {
        return adminOrderService.delete(orderId);
    }
}
