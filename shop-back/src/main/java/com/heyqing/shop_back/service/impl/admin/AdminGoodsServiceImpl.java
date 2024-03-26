package com.heyqing.shop_back.service.impl.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.constant.OSSTypeConstant;
import com.heyqing.shop_back.dao.entity.CategoryEntity;
import com.heyqing.shop_back.dao.entity.SortEntity;
import com.heyqing.shop_back.dao.entity.GoodsEntity;
import com.heyqing.shop_back.dao.mapper.admin.AdminSortMapper;
import com.heyqing.shop_back.dao.mapper.CategoryMapper;
import com.heyqing.shop_back.dao.mapper.GoodsMapper;
import com.heyqing.shop_back.dao.pojo.*;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.admin.AdminGoodsService;
import com.heyqing.shop_back.utils.IdUtil;
import com.heyqing.shop_back.utils.OSSUtil;
import com.heyqing.shop_back.vo.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


import java.util.*;

/**
 * ClassName:AdminGoodsServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/2/7
 * @Author:Heyqing
 */
@Service
public class AdminGoodsServiceImpl implements AdminGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private AdminSortMapper adminSortMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    private final OSSUtil aliyunOSSUtil;

    @Autowired
    public AdminGoodsServiceImpl(OSSUtil aliyunOSSUtil) {
        this.aliyunOSSUtil = aliyunOSSUtil;
    }

    /**
     * 获取商品列表
     *
     * @param adminPageListPO
     * @return
     */
    @Override
    public Result getList(AdminPageListPO adminPageListPO) {
        String query = adminPageListPO.getQuery().trim();
        Page<GoodsEntity> page = new Page<>(adminPageListPO.getPageNum(), adminPageListPO.getPageSize());
        Page<GoodsEntity> pageResult = goodsMapper.goodsList(page, query);
        List<GoodsEntity> goodsEntityList = pageResult.getRecords();
        List<GoodsVO> goodsVOList = copyList2Goods(goodsEntityList);
        Map<String, Object> map = new HashMap<>();
        map.put("productList", goodsVOList);
        map.put("total", pageResult.getTotal());
        return Result.success(map, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * avatar上传
     *
     * @param file
     * @return
     */
    @Override
    public Result uploadImage(MultipartFile file) {
        String uploadUrl = upload(file,  OSSTypeConstant.GOODS_AVATAR_PATH);
        if (uploadUrl == null){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(uploadUrl,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result uploadVideo(MultipartFile file) {
        String uploadUrl = upload(file,  OSSTypeConstant.VIDEO_PATH);
        if (uploadUrl == null){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(uploadUrl,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result uploadSwiper(MultipartFile file) {
        String uploadUrl = upload(file,  OSSTypeConstant.SWIPER_PATH);
        if (uploadUrl == null){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(uploadUrl,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result uploadDetail(MultipartFile file) {
        String uploadUrl = upload(file,  OSSTypeConstant.DETAIL_PATH);
        if (uploadUrl == null){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(uploadUrl,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result uploadParam(MultipartFile file) {
        String uploadUrl = upload(file,  OSSTypeConstant.PARAM_PATH);
        if (uploadUrl == null){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(uploadUrl,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result save(GoodsSavaPO goodsSavaPO) {
        if (Objects.isNull(goodsSavaPO)){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        GoodsEntity goodsEntity = addGoods(goodsSavaPO);
        if (goodsSavaPO.getGoodsId() == null || goodsSavaPO.getGoodsId() == -1){
            //添加
            Long id = IdUtil.getCurrentId();
            goodsEntity.setGoodsId(id);
            goodsMapper.insert(goodsEntity);
        }else {
            //修改
            goodsEntity.setGoodsId(goodsSavaPO.getGoodsId());
            goodsEntity.setUpdateTime(new Date());
            goodsMapper.updateById(goodsEntity);
        }
        return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result delete(Long goodsId) {
        try{
            goodsMapper.deleteById(goodsId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result goods(GoodsSearchPO goodsSearchPO) {
        LambdaQueryWrapper<GoodsEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsEntity::getGoodsName,goodsSearchPO.getQuery());
        queryWrapper.select(GoodsEntity::getGoodsName,GoodsEntity::getGoodsId,GoodsEntity::getGoodsPrice);
        try {
            GoodsEntity goodsEntity = goodsMapper.selectOne(queryWrapper);
            if (Objects.isNull(goodsEntity)){
                return Result.fail(new Mate(500,"暂无此商品"));
            }
            SceneGoods sceneGoods = copy2Scene(goodsEntity);
            return Result.success(sceneGoods ,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(new Mate(500,"暂无此商品"));
        }
    }



    /******************************************************private******************************************/

    private SceneGoods copy2Scene(GoodsEntity goodsEntity) {
        SceneGoods sceneGoods = new SceneGoods();
        sceneGoods.setGoodsId(String.valueOf(goodsEntity.getGoodsId()));
        sceneGoods.setGoodsName(goodsEntity.getGoodsName());
        sceneGoods.setGoodsPrice(goodsEntity.getGoodsPrice());
        return sceneGoods;
    }

    private GoodsEntity addGoods(GoodsSavaPO goodsSavaPO) {
        if (Objects.isNull(goodsSavaPO)){
            return null;
        }
        GoodsEntity goodsEntity = new GoodsEntity();
        //拼接字符串
        String goodsParam =null;
        if (!Objects.isNull(goodsSavaPO.getGoodsParam())){
            goodsParam= String.join(",", goodsSavaPO.getGoodsParam());
        }
        String goodsDetail = null;
        if (!Objects.isNull(goodsSavaPO.getGoodsDetail())){
            goodsDetail= String.join(",", goodsSavaPO.getGoodsDetail());
        }
        String goodsPhoto = null;
        if (!Objects.isNull(goodsSavaPO.getGoodsPhoto())){
            goodsPhoto= String.join(",", goodsSavaPO.getGoodsPhoto());
        }

        //获取id
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("sort_id",goodsSavaPO.getSortName());

        SortEntity sortEntity = adminSortMapper.selectOne(queryWrapper);
        Long sortId = null;
        if (!Objects.isNull(sortEntity)){
            sortId = sortEntity.getSortId();
        }
        Long categoryId = null;
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("category_id",goodsSavaPO.getCategoryName());
        CategoryEntity categoryEntity = categoryMapper.selectOne(wrapper);
        if (!Objects.isNull(categoryEntity)){
            categoryId = categoryEntity.getCategoryId();
        }
        String goodsVideo = goodsSavaPO.getGoodsVideo();
        if (Strings.isBlank(goodsVideo)){
            goodsVideo = null;
        }

        goodsEntity.setGoodsType(goodsSavaPO.getGoodsType());
        goodsEntity.setGoodsPrice(goodsSavaPO.getGoodsPrice());
        goodsEntity.setGoodsName(goodsSavaPO.getGoodsName());
        goodsEntity.setGoodsVideo(goodsVideo);
        goodsEntity.setGoodsDesc(goodsSavaPO.getGoodsDesc());
        goodsEntity.setGoodsParam(goodsParam);
        goodsEntity.setGoodsDetail(goodsDetail);
        goodsEntity.setGoodsPhoto(goodsPhoto);
        goodsEntity.setGoodsTitle(goodsSavaPO.getGoodsTitle());
        goodsEntity.setGoodsAvatar(goodsSavaPO.getGoodsAvatar());
        goodsEntity.setSortId(sortId);
        goodsEntity.setCategoryId(categoryId);

        return goodsEntity;
    }

    private String upload(MultipartFile file,  String typePath) {
        // 返回客户端文件系统中的原始文件名
        String fileName = file.getOriginalFilename();
        try {
            if (file != null) {
                // 判定文件名是否为 ""
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    // 以字节数组的形式返回文件的内容,再输出到文件输出流中
                    os.write(file.getBytes());
                    os.close();
                    // 将接受的文件传输到给定的目标文件 file-->newFile
                    file.transferTo(newFile);
                    String timeStamp = String.valueOf(System.currentTimeMillis());
                    fileName = timeStamp + fileName.substring(fileName.lastIndexOf("."));
                    String path;
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                        // images
                        path = "images/" + typePath + fileName;
                    } else if (fileName.endsWith(".mp4")) {
                        path = "video/" + typePath + fileName;
                    } else {
                        return null;
                    }
                    // 上传到OSS
                    String uploadUrl = aliyunOSSUtil.upLoad(newFile, path);
                    newFile.delete();
                    if (uploadUrl != null) {
                        return uploadUrl;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private GoodsVO copy(GoodsEntity goodsEntity) {
        GoodsVO goodsVO = new GoodsVO();

        //1、goodsPhoto
        String[] goodsPhotoParts = goodsEntity.getGoodsPhoto().split(",");
        List<String> goodsPhotoList = new ArrayList<>();
        for (String goodsPhotoPart : goodsPhotoParts) {
            goodsPhotoList.add(goodsPhotoPart);
        }
        //2、goodsParam
        String[] goodsParamParts = goodsEntity.getGoodsParam().split(",");
        List<String> goodsParamList = new ArrayList<>();
        for (String goodsParamPart : goodsParamParts) {
            goodsParamList.add(goodsParamPart);
        }
        //3、goodsDetail
        String[] goodsDetailParts = goodsEntity.getGoodsDetail().split(",");
        List<String> goodsDetailList = new ArrayList<>();
        for (String goodsDetailPart : goodsDetailParts) {
            goodsDetailList.add(goodsDetailPart);
        }

        String cateName = categoryMapper.selectById(goodsEntity.getCategoryId()).getCategoryName();
        String sortName = adminSortMapper.selectById(goodsEntity.getSortId()).getSortName();
        goodsVO.setGoodsId(String.valueOf(goodsEntity.getGoodsId()));
        goodsVO.setCategoryName(cateName);
        goodsVO.setGoodsType(goodsEntity.getGoodsType());
        goodsVO.setGoodsVideo(goodsEntity.getGoodsVideo());
        goodsVO.setGoodsPrice(goodsEntity.getGoodsPrice());
        goodsVO.setGoodsName(goodsEntity.getGoodsName());
        goodsVO.setGoodsDesc(goodsEntity.getGoodsDesc());
        goodsVO.setGoodsParam(goodsParamList);
        goodsVO.setGoodsDetail(goodsDetailList);
        goodsVO.setSortName(sortName);
        goodsVO.setGoodsTitle(goodsEntity.getGoodsTitle());
        goodsVO.setGoodsAvatar(goodsEntity.getGoodsAvatar());
        goodsVO.setUpdateTime(goodsEntity.getUpdateTime());
        goodsVO.setCreateTime(goodsEntity.getCreateTime());
        goodsVO.setGoodsPhoto(goodsPhotoList);

        return goodsVO;
    }

    /**
     * 拷贝goods
     *
     * @param goodsEntityList
     * @return
     */
    private List<GoodsVO> copyList2Goods(List<GoodsEntity> goodsEntityList) {
        List<GoodsVO> goodsVOList = new ArrayList<>();
        for (GoodsEntity adminGoodsEntity : goodsEntityList) {
            goodsVOList.add(copy(adminGoodsEntity));
        }
        return goodsVOList;
    }
}
