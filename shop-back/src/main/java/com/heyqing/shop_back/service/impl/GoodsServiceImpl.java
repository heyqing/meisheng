package com.heyqing.shop_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heyqing.shop_back.dao.entity.*;
import com.heyqing.shop_back.dao.mapper.*;
import com.heyqing.shop_back.dao.pojo.GetFloorPO;
import com.heyqing.shop_back.dao.pojo.GetSortPO;
import com.heyqing.shop_back.dao.pojo.GetSwiperPO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.GoodsService;
import com.heyqing.shop_back.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:GoodsServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/1/25
 * @Author:Heyqing
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SortMapper sortMapper;
    @Autowired
    private SearchMapper searchMapper;

    /**
     * 获取轮播图
     *
     * @return
     */
    @Override
    public Result getSwiper() {
        GetSwiperPO getSwiperPO = new GetSwiperPO();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("goods_type", getSwiperPO.getGoodsType());
        queryWrapper.select("goods_id", "goods_avatar");
        List<GoodsEntity> swiperList = goodsMapper.selectList(queryWrapper);
        List<GetSwiperVO> swiperVOList = copyList2Swiper(swiperList);
        return Result.success(swiperVOList, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 获取分类大类
     *
     * @return
     */
    @Override
    public Result getSort() {
        GetSortPO getSortPO = new GetSortPO();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sort_type", getSortPO.getSortType());
        queryWrapper.select("sort_id", "sort_name", "sort_avatar");
        List<SortEntity> sortList = sortMapper.selectList(queryWrapper);
        List<GetSortVO> sortVoList = copyList2Sort(sortList);
        return Result.success(sortVoList, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 获取楼层商品
     *
     * @return
     */
    @Override
    public Result getFloor(GetFloorPO getFloorPO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("goods_type", getFloorPO.getFloorType());
        queryWrapper.select("goods_id", "goods_avatar", "goods_price", "goods_name", "goods_title", "goods_desc");
        queryWrapper.last(" LIMIT " + getFloorPO.getResponseNum() + " OFFSET " + getFloorPO.getResponseNum() * getFloorPO.getSelectNum());
        List<GoodsEntity> floorList = goodsMapper.selectList(queryWrapper);
        List<GetFloorVO> floorVOList = copyList2Floor(floorList);
        return Result.success(floorVOList, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 获取商品详情
     *
     * @param goodsId
     * @return
     */
    @Override
    public Result getGoodsDetail(Long goodsId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("goods_id", goodsId);
        queryWrapper.select("goods_id", "goods_avatar", "goods_video", "goods_price", "goods_name", "goods_desc", "goods_param", "goods_detail", "goods_photo");
        List<GoodsEntity> detailList = goodsMapper.selectList(queryWrapper);
        List<GetDetailVO> detailVOList = copyList2Detail(detailList);
        return Result.success(detailVOList, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 获取分类商品
     * <p>
     * 与获取楼层的返回值相同
     *
     * @param categoryId
     * @return
     */
    @Override
    public Result getCategoryGoods(Long categoryId) {
        GetFloorPO getFloorPO = new GetFloorPO();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.select("goods_id", "goods_avatar", "goods_price", "goods_name", "goods_title", "goods_desc");
        List<GoodsEntity> floorList = goodsMapper.selectList(queryWrapper);
        List<GetFloorVO> floorVOList = copyList2Floor(floorList);
        return Result.success(floorVOList, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 商品搜索
     *
     * @param q
     * @return
     */
    @Override
    public Result search(String q) {
        List<SearchEntity> searchEntityList = searchMapper.selectListByQ(q);
        List<SearchVO> searchVOList = copyList2Search(searchEntityList);
        return Result.success(searchVOList, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }


    /****************************************************private***********************************************/

    private SearchVO copy(SearchEntity searchEntity){
        SearchVO searchVO = new SearchVO();
        searchVO.setGoodsAvatar(searchEntity.getGoodsAvatar());
        searchVO.setGoodsId(String.valueOf(searchEntity.getGoodsId()));
        searchVO.setGoodsPrice(searchEntity.getGoodsPrice());
        searchVO.setGoodsName(searchEntity.getGoodsName());
        searchVO.setGoodsTitle(searchEntity.getGoodsTitle());
        searchVO.setGoodsDesc(searchEntity.getGoodsDesc());
        BeanUtils.copyProperties(searchEntity, searchVO);
        return searchVO;
    }

    /**
     * 拷贝swiper
     *
     * @param goodsEntity
     * @return
     */
    private GetSwiperVO copy2Swiper(GoodsEntity goodsEntity) {
        GetSwiperVO getSwiperVO = new GetSwiperVO();
        getSwiperVO.setGoodsId(String.valueOf(goodsEntity.getGoodsId()));
        getSwiperVO.setGoodsAvatar(goodsEntity.getGoodsAvatar());
        BeanUtils.copyProperties(goodsEntity, getSwiperVO);
        return getSwiperVO;
    }

    /**
     * 拷贝sort
     *
     * @param sortEntity
     * @return
     */
    private GetSortVO copy(SortEntity sortEntity) {
        GetSortVO getSortVo = new GetSortVO();
        getSortVo.setSortId(String.valueOf(sortEntity.getSortId()));
        getSortVo.setSortName(sortEntity.getSortName());
        getSortVo.setSortAvatar(sortEntity.getSortAvatar());
        BeanUtils.copyProperties(sortEntity, getSortVo);
        return getSortVo;
    }

    private GetFloorVO copy2Floor(GoodsEntity goodsEntity) {
        GetFloorVO getFloorVO = new GetFloorVO();
        getFloorVO.setGoodsId(String.valueOf(goodsEntity.getGoodsId()));
        getFloorVO.setGoodsAvatar(goodsEntity.getGoodsAvatar());
        getFloorVO.setGoodsPrice(goodsEntity.getGoodsPrice());
        getFloorVO.setGoodsName(goodsEntity.getGoodsName());
        getFloorVO.setGoodsTitle(goodsEntity.getGoodsTitle());
        getFloorVO.setGoodsDesc(goodsEntity.getGoodsDesc());
        BeanUtils.copyProperties(goodsEntity, getFloorVO);
        return getFloorVO;
    }

    /**
     * 拷贝detail
     * <p>
     * 需将goodsPhoto、goodsParam、goodsDetail按 , 分割并将其存入list
     *
     * @param detailEntity
     * @return
     */
    private GetDetailVO copy(GoodsEntity detailEntity) {
        GetDetailVO getDetailVO = new GetDetailVO();
        getDetailVO.setGoodsId(String.valueOf(detailEntity.getGoodsId()));
        getDetailVO.setGoodsAvatar(detailEntity.getGoodsAvatar());
        getDetailVO.setGoodsName(detailEntity.getGoodsName());
        getDetailVO.setGoodsDesc(detailEntity.getGoodsDesc());
        getDetailVO.setGoodsPrice(detailEntity.getGoodsPrice());
        getDetailVO.setGoodsVideo(detailEntity.getGoodsVideo());
        //1、goodsPhoto
        String[] goodsPhotoParts = detailEntity.getGoodsPhoto().split(",");
        List<String> goodsPhotoList = new ArrayList<>();
        for (String goodsPhotoPart : goodsPhotoParts) {
            goodsPhotoList.add(goodsPhotoPart);
        }
        //2、goodsParam
        String[] goodsParamParts = detailEntity.getGoodsParam().split(",");
        List<String> goodsParamList = new ArrayList<>();
        for (String goodsParamPart : goodsParamParts) {
            goodsParamList.add(goodsParamPart);
        }
        //3、goodsDetail
        String[] goodsDetailParts = detailEntity.getGoodsDetail().split(",");
        List<String> goodsDetailList = new ArrayList<>();
        for (String goodsDetailPart : goodsDetailParts) {
            goodsDetailList.add(goodsDetailPart);
        }
        getDetailVO.setGoodsPhoto(goodsPhotoList);
        getDetailVO.setGoodsParam(goodsParamList);
        getDetailVO.setGoodsDetail(goodsDetailList);
        BeanUtils.copyProperties(detailEntity, getDetailVO);
        return getDetailVO;
    }

    /**
     * 拷贝swiper
     *
     * @param getSwiperEntityList
     * @return
     */
    private List<GetSwiperVO> copyList2Swiper(List<GoodsEntity> getSwiperEntityList) {
        List<GetSwiperVO> swiperVOList = new ArrayList<>();
        for (GoodsEntity goodsEntity : getSwiperEntityList) {
            swiperVOList.add(copy2Swiper(goodsEntity));
        }
        return swiperVOList;
    }

    /**
     * 拷贝sort
     *
     * @param sortEntityList
     * @return
     */
    private List<GetSortVO> copyList2Sort(List<SortEntity> sortEntityList) {
        List<GetSortVO> sortVoList = new ArrayList<>();
        for (SortEntity sortEntity : sortEntityList) {
            sortVoList.add(copy(sortEntity));
        }
        return sortVoList;
    }

    /**
     * 拷贝floor
     *
     * @param getFloorEntityList
     * @return
     */
    private List<GetFloorVO> copyList2Floor(List<GoodsEntity> getFloorEntityList) {
        List<GetFloorVO> floorVOList = new ArrayList<>();
        for (GoodsEntity getFloorEntity : getFloorEntityList) {
            floorVOList.add(copy2Floor(getFloorEntity));
        }
        return floorVOList;
    }

    /**
     * 拷贝detail
     *
     * @param detailList
     * @return
     */
    private List<GetDetailVO> copyList2Detail(List<GoodsEntity> detailList) {
        List<GetDetailVO> detailVOList = new ArrayList<>();
        for (GoodsEntity detailEntity : detailList) {
            detailVOList.add(copy(detailEntity));
        }
        return detailVOList;
    }

    /**
     * 拷贝search
     *
     * @param searchEntityList
     * @return
     */
    private List<SearchVO> copyList2Search(List<SearchEntity> searchEntityList) {
        List<SearchVO> searchVOList = new ArrayList<>();
        for (SearchEntity searchEntity : searchEntityList) {
            searchVOList.add(copy(searchEntity));
        }
        return searchVOList;
    }
}
