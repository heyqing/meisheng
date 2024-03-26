package com.heyqing.shop_back.service.impl.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.dao.entity.ChoiceEntity;
import com.heyqing.shop_back.dao.entity.OrderEntity;
import com.heyqing.shop_back.dao.entity.SortEntity;
import com.heyqing.shop_back.dao.mapper.ChoiceMapper;
import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.admin.AdminChoiceService;
import com.heyqing.shop_back.vo.AdminChoiceVO;
import com.heyqing.shop_back.vo.GetSortVO;
import com.heyqing.shop_back.vo.Mate;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:AdminChoiceServiceImpl
 * Package:com.heyqing.shop_back.service.impl.admin
 * Description:
 *
 * @Date:2024/3/10
 * @Author:Heyqing
 */
@Service
public class AdminChoiceServiceImpl implements AdminChoiceService {

    @Autowired
    private ChoiceMapper choiceMapper;

    @Override
    public Result getList(AdminPageListPO adminPageListPO) {
        String query = adminPageListPO.getQuery().trim();
        Page<ChoiceEntity> page = new Page<>(adminPageListPO.getPageNum(), adminPageListPO.getPageSize());
        Page<ChoiceEntity> pageResult = choiceMapper.goodsList(page, query);
        List<ChoiceEntity> choiceEntityList = pageResult.getRecords();
        List<AdminChoiceVO> choiceVOList =copyList2Choice(choiceEntityList);
        Map<String, Object> map = new HashMap<>();
        map.put("choiceList", choiceVOList);
        map.put("total", pageResult.getTotal());
        return Result.success(map, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }


    @Override
    public Result delete(Long choiceId) {
        try {
            choiceMapper.deleteById(choiceId);
            return Result.success(null,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
        }catch (Exception e){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
    }

    /******************************************************private*********************************************/


    private List<AdminChoiceVO> copyList2Choice(List<ChoiceEntity> choiceEntityList) {
        List<AdminChoiceVO> choiceVOList = new ArrayList<>();
        for (ChoiceEntity choiceEntity : choiceEntityList) {
            choiceVOList.add(copy2Choice(choiceEntity));
        }
        return choiceVOList;
    }

    private AdminChoiceVO copy2Choice(ChoiceEntity choiceEntity) {
        AdminChoiceVO choiceVO = new AdminChoiceVO();

        choiceVO.setChoiceId(String.valueOf(choiceEntity.getChoiceId()));
        choiceVO.setChoiceShop(choiceEntity.getChoiceShop());
        choiceVO.setChoiceCurrentRegion(choiceEntity.getChoiceCurrentRegion());
        choiceVO.setChoiceChainStore(choiceEntity.getChoiceChainStore());
        choiceVO.setChoiceGoods(choiceEntity.getChoiceGoods());
        choiceVO.setChoicePhone(choiceEntity.getChoicePhone());
        choiceVO.setCreateTime(choiceEntity.getCreateTime());
        choiceVO.setUpdateTime(choiceEntity.getUpdateTime());
        return choiceVO;
    }
}
