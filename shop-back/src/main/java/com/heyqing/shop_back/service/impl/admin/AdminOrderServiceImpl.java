package com.heyqing.shop_back.service.impl.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.OrderDetailEntity;
import com.heyqing.shop_back.dao.entity.OrderEntity;
import com.heyqing.shop_back.dao.entity.SortEntity;
import com.heyqing.shop_back.dao.mapper.OrderDetailMapper;
import com.heyqing.shop_back.dao.mapper.OrderMapper;
import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.UpdateStatusPO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.admin.AdminOrderService;
import com.heyqing.shop_back.vo.GetSortVO;
import com.heyqing.shop_back.vo.Mate;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName:AdminOrderServiceImpl
 * Package:com.heyqing.shop_back.service.impl.admin
 * Description:
 *
 * @Date:2024/3/7
 * @Author:Heyqing
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public Result getList(AdminPageListPO adminPageListPO) {
        String query = adminPageListPO.getQuery().trim();
        Page<OrderEntity> page = new Page<>(adminPageListPO.getPageNum(), adminPageListPO.getPageSize());
        Page<OrderEntity> pageResult = orderMapper.getPageList(page,query);
        List<OrderEntity> orderEntityList = pageResult.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("orderList", orderEntityList);
        map.put("total", pageResult.getTotal());
        return Result.success(map, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result getOrderDetail(Long orderId) {
        LambdaQueryWrapper<OrderDetailEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetailEntity::getMid,orderId);
        List<OrderDetailEntity> orderDetailEntityList = orderDetailMapper.selectList(queryWrapper);
        return Result.success(orderDetailEntityList,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result updateStatus(UpdateStatusPO updateStatusPO) {
        OrderEntity orderEntity = orderMapper.selectById(updateStatusPO.getOrderId());
        if (Objects.isNull(orderEntity)){
            return Result.fail(new Mate(500,"暂无此订单"));
        }
        Integer state = orderEntity.getState();
        if (state == 1){
            return Result.fail(new Mate(500 ,"该订单尚未支付"));
        }
        orderEntity.setState(updateStatusPO.getState());
        orderMapper.updateById(orderEntity);
        return Result.success(null,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delete(Long orderId) {
        try {
            orderMapper.deleteById(orderId);
            LambdaQueryWrapper<OrderDetailEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OrderDetailEntity::getMid,orderId);
            orderDetailMapper.delete(queryWrapper);
            return Result.success(null,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(new Mate(500,"删除失败"));
        }
    }

    /***************************************************private*****************************************************/
}
