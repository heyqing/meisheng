package com.heyqing.shop_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heyqing.shop_back.dao.entity.ChoiceAvatarEntity;
import com.heyqing.shop_back.dao.entity.ChoiceEntity;
import com.heyqing.shop_back.dao.entity.GoodsEntity;
import com.heyqing.shop_back.dao.entity.SceneEntity;
import com.heyqing.shop_back.dao.mapper.ChoiceAvatarMapper;
import com.heyqing.shop_back.dao.mapper.ChoiceMapper;
import com.heyqing.shop_back.dao.mapper.GoodsMapper;
import com.heyqing.shop_back.dao.mapper.SceneMapper;
import com.heyqing.shop_back.dao.pojo.SceneSavePO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.SceneService;
import com.heyqing.shop_back.utils.IdUtil;
import com.heyqing.shop_back.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName:SceneServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/2/28
 * @Author:Heyqing
 */
@Service
public class SceneServiceImpl implements SceneService {
    private static final Long CHOICE_AVATAR_ID = 2024031017L;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ChoiceAvatarMapper choiceAvatarMapper;
    @Autowired
    private ChoiceMapper choiceMapper;

    /**
     * 获取轮播图
     *
     * @return
     */
    @Override
    public Result getSwiper() {
        LambdaQueryWrapper<SceneEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SceneEntity::getSceneType, "swiper");
        queryWrapper.select(SceneEntity::getSceneAvatar, SceneEntity::getSceneId);
        List<SceneEntity> sceneEntityList = sceneMapper.selectList(queryWrapper);
        List<SceneSwiperVO> sceneSwiperVOList = copyList2Swiper(sceneEntityList);
        return Result.success(sceneSwiperVOList, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result getFloor() {
        LambdaQueryWrapper<SceneEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SceneEntity::getSceneType, "floor");
        queryWrapper.select(SceneEntity::getSceneAvatar, SceneEntity::getSceneId, SceneEntity::getSceneName);
        List<SceneEntity> sceneEntityList = sceneMapper.selectList(queryWrapper);
        List<SceneFloorVO> sceneFloorVOList = copyList2Floor(sceneEntityList);
        return Result.success(sceneFloorVOList, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result getDetail(Long sceneId) {
        LambdaQueryWrapper<SceneEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SceneEntity::getSceneId, sceneId);
        SceneEntity sceneEntity = sceneMapper.selectOne(queryWrapper);
        SceneDetailVO sceneDetailVO = copy2Detail(sceneEntity);
        return Result.success(sceneDetailVO, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result getCustom() {
        ChoiceAvatarEntity avatarEntity = choiceAvatarMapper.selectById(CHOICE_AVATAR_ID);
        return Result.success(avatarEntity, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result save(SceneSavePO sceneSavePO) {
        ChoiceEntity choiceEntity = new ChoiceEntity();

        //地址
        String address = String.join(",", sceneSavePO.getCurrentRegion());
        //连锁店？
        Integer isChainStore = sceneSavePO.getIsSwitch() ? 1 : 0;

        choiceEntity.setChoiceId(IdUtil.getCurrentId());
        choiceEntity.setChoiceShop(sceneSavePO.getShopType());
        choiceEntity.setChoiceGoods(sceneSavePO.getGoodsType());
        choiceEntity.setChoicePhone(sceneSavePO.getPhone());
        choiceEntity.setChoiceCurrentRegion(address);
        choiceEntity.setChoiceChainStore(isChainStore);

        try {
            choiceMapper.insert(choiceEntity);
            return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
    }

    /**************************************************private********************************************/
    private SceneDetailVO copy2Detail(SceneEntity sceneEntity) {
        SceneDetailVO sceneDetailVO = new SceneDetailVO();
        //sceneDetail
        String[] sceneDetails = sceneEntity.getSceneDetail().split(",");
        List<String> sceneDetailList = new ArrayList<>();
        for (String sceneDetail : sceneDetails) {
            sceneDetailList.add(sceneDetail);
        }
        //sceneGoods
        List<Long> sceneGoodsIdList = Arrays.stream(sceneEntity.getSceneGoods().split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<GoodsEntity> floorEntityList = new ArrayList<>();
        for (Long goodsId : sceneGoodsIdList) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("goods_id", goodsId);
            floorEntityList.add(goodsMapper.selectOne(queryWrapper));
        }
        List<GetFloorVO> sceneGoods = copyList2SceneFloor(floorEntityList);

        sceneDetailVO.setSceneId(String.valueOf(sceneEntity.getSceneId()));
        sceneDetailVO.setSceneName(sceneEntity.getSceneName());
        sceneDetailVO.setSceneDesc(sceneEntity.getSceneDesc());
        sceneDetailVO.setSceneAvatar(sceneEntity.getSceneAvatar());
        sceneDetailVO.setSceneGoods(sceneGoods);
        sceneDetailVO.setSceneDetail(sceneDetailList);

        return sceneDetailVO;
    }

    private GetFloorVO copy(GoodsEntity getFloorEntity) {
        GetFloorVO getFloorVO = new GetFloorVO();
        getFloorVO.setGoodsId(String.valueOf(getFloorEntity.getGoodsId()));
        getFloorVO.setGoodsAvatar(getFloorEntity.getGoodsAvatar());
        getFloorVO.setGoodsPrice(getFloorEntity.getGoodsPrice());
        getFloorVO.setGoodsName(getFloorEntity.getGoodsName());
        getFloorVO.setGoodsTitle(getFloorEntity.getGoodsTitle());
        getFloorVO.setGoodsDesc(getFloorEntity.getGoodsDesc());
        BeanUtils.copyProperties(getFloorEntity, getFloorVO);
        return getFloorVO;
    }

    private List<GetFloorVO> copyList2SceneFloor(List<GoodsEntity> getFloorEntityList) {
        List<GetFloorVO> floorVOList = new ArrayList<>();
        for (GoodsEntity getFloorEntity : getFloorEntityList) {
            floorVOList.add(copy(getFloorEntity));
        }
        return floorVOList;
    }

    private Object copy(SceneEntity sceneEntity) {
        SceneFloorVO sceneFloorVO = new SceneFloorVO();
        SceneSwiperVO sceneSwiperVO = new SceneSwiperVO();
        sceneFloorVO.setSceneId(String.valueOf(sceneEntity.getSceneId()));
        sceneFloorVO.setSceneAvatar(sceneEntity.getSceneAvatar());
        sceneFloorVO.setSceneName(sceneEntity.getSceneName());
        sceneSwiperVO.setSceneId(String.valueOf(sceneEntity.getSceneId()));
        sceneSwiperVO.setSceneAvatar(sceneEntity.getSceneAvatar());
        if (sceneEntity.getSceneName() == null) {
            return sceneSwiperVO;
        }
        return sceneFloorVO;
    }

    private List<SceneSwiperVO> copyList2Swiper(List<SceneEntity> sceneEntityList) {
        List<SceneSwiperVO> sceneSwiperVOList = new ArrayList<>();
        for (SceneEntity sceneEntity : sceneEntityList) {
            sceneSwiperVOList.add((SceneSwiperVO) copy(sceneEntity));
        }
        return sceneSwiperVOList;
    }

    private List<SceneFloorVO> copyList2Floor(List<SceneEntity> sceneEntityList) {
        List<SceneFloorVO> sceneFloorVOList = new ArrayList<>();
        for (SceneEntity sceneEntity : sceneEntityList) {
            sceneFloorVOList.add((SceneFloorVO) copy(sceneEntity));
        }
        return sceneFloorVOList;
    }
}
