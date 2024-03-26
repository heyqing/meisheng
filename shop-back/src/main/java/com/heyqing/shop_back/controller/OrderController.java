package com.heyqing.shop_back.controller;

import com.heyqing.shop_back.dao.pojo.CreateOrderPO;
import com.heyqing.shop_back.dao.pojo.PreparePayOrderPO;
import com.heyqing.shop_back.service.OrderService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:OrderController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
@RestController
@RequestMapping("/my/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param token
     * @param createOrderPO
     * @return
     */
    @PostMapping("create")
    public Result createOrder(@RequestHeader(value = "token") String token, @RequestBody CreateOrderPO createOrderPO) {
        return orderService.createOrder(token, createOrderPO);
    }

    /**
     * 预支付
     *
     * @param token
     * @param preparePayOrderPO
     * @return
     */
    @PostMapping("preparePay")
    public Result preparePayOrder(@RequestHeader(value = "token") String token, @RequestBody PreparePayOrderPO preparePayOrderPO) {
        return orderService.preparePayOrder(token, preparePayOrderPO);
    }

    /**
     * 订单查询 type值 0 全部订单 1 待付款 2 待收货 3 退款/退货
     *
     * @param type
     * @param page
     * @param pageSize
     * @param token
     * @return
     */
    @GetMapping("list")
    public Result list(Integer type, Integer page, Integer pageSize, @RequestHeader(value = "token") String token) {
        return orderService.list(type, page, pageSize, token);
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @param token
     * @return
     */
    @GetMapping("detail/{id}")
    public Result detail(@PathVariable("id") Long orderId,@RequestHeader(value = "token") String token) {
        return orderService.detail(orderId,token);
    }
}
