package com.heyqing.shop_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.OrderDetailEntity;
import com.heyqing.shop_back.dao.entity.OrderEntity;
import com.heyqing.shop_back.dao.entity.UserEntity;
import com.heyqing.shop_back.dao.mapper.OrderDetailMapper;
import com.heyqing.shop_back.dao.mapper.OrderMapper;
import com.heyqing.shop_back.dao.mapper.UserMapper;
import com.heyqing.shop_back.dao.pojo.CreateOrderPO;
import com.heyqing.shop_back.dao.pojo.Goods;
import com.heyqing.shop_back.dao.pojo.PreparePayOrderPO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.OrderService;
import com.heyqing.shop_back.utils.DateUtil;
import com.heyqing.shop_back.utils.IdUtil;
import com.heyqing.shop_back.utils.JwtUtils;
import com.heyqing.shop_back.utils.StringUtil;
import com.heyqing.shop_back.vo.Mate;
import com.heyqing.shop_back.vo.Result;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * ClassName:OrderServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result createOrder(String token, CreateOrderPO createOrderPO) {
        String orderNo = null;
        if (StringUtil.isNotEmpty(token)) {
            Claims claims = JwtUtils.validateJWT(token).getClaims();
            if (claims != null) {
                OrderEntity orderEntity = new OrderEntity();
                String openId = claims.getId();
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("openid", openId);
                UserEntity user = userMapper.selectOne(queryWrapper);
                Long userId = user.getUserId();
                orderEntity.setOrderId(IdUtil.getCurrentId());
                orderEntity.setUserId(userId);
                orderNo = "MeiSheng" + DateUtil.getCurrentDateStr();
                orderEntity.setOrderNo(orderNo);
                orderEntity.setCreateTime(new Date());
                orderEntity.setTotalPrice(createOrderPO.getTotalPrice());
                orderEntity.setAddress(createOrderPO.getAddress());
                orderEntity.setConsignee(createOrderPO.getConsignee());
                orderEntity.setTellPhone(createOrderPO.getTellPhone());
                //插入订单信息
                List<Goods> goods = createOrderPO.getGoods();
                orderMapper.insert(orderEntity);
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                for (Goods good : goods) {
                    orderDetailEntity.setOrderDetailId(IdUtil.getCurrentId());
                    orderDetailEntity.setMid(orderEntity.getOrderId());
                    orderDetailEntity.setGoodsId(good.getGoodsId());
                    orderDetailEntity.setGoodsNum(good.getGoodsNum());
                    orderDetailEntity.setGoodsPrice(good.getGoodsPrice());
                    orderDetailEntity.setGoodsName(good.getGoodsName());
                    orderDetailEntity.setGoodsAvatar(good.getGoodsAvatar());
                    orderDetailMapper.insert(orderDetailEntity);
                }

            } else {
                return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
            }
        } else {
            return Result.fail(new Mate(MateEnum.NO_ACCESS.getCode(), MateEnum.NO_ACCESS.getMsg()));
        }
        return Result.success(orderNo, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result preparePayOrder(String token, PreparePayOrderPO preparePayOrderPO) {
        return null;
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
    @Override
    public Result list(Integer type, Integer page, Integer pageSize, String token) {

        System.out.println(type);

        String openId = checkToken(token);
        if (StringUtil.isNotEmpty(openId)){

            LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserEntity::getOpenid,openId);
            UserEntity userEntity = userMapper.selectOne(wrapper);
            Long userId = userEntity.getUserId();
            Page<OrderEntity> pageOrder = new Page<>(page, pageSize);

            LambdaQueryWrapper<OrderEntity> queryWrapper = new LambdaQueryWrapper<>();
            //订单查询 type值 0 全部订单 1 待付款 2 待收货 3 退款/退货
            queryWrapper.eq(OrderEntity::getUserId,userId);
            if (type == 0){

            }else {
                queryWrapper.eq(OrderEntity::getState, type);
            }


            Page<OrderEntity> orderResult = orderMapper.selectPage(pageOrder, queryWrapper);

            List<OrderEntity> orderList = orderResult.getRecords();
            Map<String ,Object> map = new HashMap<>();
            map.put("orderList",orderList);
            map.put("total",orderResult.getTotal());
            map.put("totalPage",orderResult.getPages());


            return Result.success(map,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
        }else {
            return Result.fail(new Mate(500, "鉴权失败！"));
        }

    }

    @Override
    public Result detail(Long orderId,String token) {
        Map<String ,Object> resultMap = new HashMap<>();
        String openId = checkToken(token);
        if (StringUtil.isNotEmpty(openId)){
            LambdaQueryWrapper<OrderDetailEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OrderDetailEntity::getMid,orderId);
            List<OrderDetailEntity> orderDetailEntityList = orderDetailMapper.selectList(queryWrapper);
            OrderEntity orderEntity = orderMapper.selectById(orderId);
            if (Objects.isNull(orderDetailEntityList)&&Objects.isNull(orderEntity)){
                return Result.fail(new Mate(500,"暂无此商品"));
            }else {
                resultMap.put("orderDetail",orderDetailEntityList);
                resultMap.put("order",orderEntity);
                return Result.success(resultMap,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
            }
        }
        return Result.fail(new Mate(500,"暂无此商品"));
    }

    /****************************************************private***********************************************/
//    https://pay.weixin.qq.com/static/applyment_guide/applyment_detail_miniapp.shtml

    private String  checkToken(String token){
        if (StringUtil.isNotEmpty(token)) {
            Claims claims = JwtUtils.validateJWT(token).getClaims();
            if (claims != null) {
                String openId = claims.getId();
                System.out.println(openId);
                return openId;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
