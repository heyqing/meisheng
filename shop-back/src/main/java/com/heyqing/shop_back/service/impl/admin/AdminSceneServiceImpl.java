package com.heyqing.shop_back.service.impl.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.constant.OSSTypeConstant;
import com.heyqing.shop_back.dao.entity.GoodsEntity;
import com.heyqing.shop_back.dao.entity.SceneEntity;
import com.heyqing.shop_back.dao.mapper.GoodsMapper;
import com.heyqing.shop_back.dao.mapper.SceneMapper;
import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.AdminSceneSavePO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.admin.AdminSceneService;
import com.heyqing.shop_back.utils.IdUtil;
import com.heyqing.shop_back.utils.OSSUtil;
import com.heyqing.shop_back.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * ClassName:AdminSceneServiceImpl
 * Package:com.heyqing.shop_back.service.impl.admin
 * Description:
 *
 * @Date:2024/3/2
 * @Author:Heyqing
 */
@Service
public class AdminSceneServiceImpl implements AdminSceneService {

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    private final OSSUtil aliyunOSSUtil;

    @Autowired
    public AdminSceneServiceImpl(OSSUtil aliyunOSSUtil) {
        this.aliyunOSSUtil = aliyunOSSUtil;
    }

    @Override
    public Result getList(AdminPageListPO adminPageListPO) {
        String query = adminPageListPO.getQuery().trim();
        Page<SceneEntity> page = new Page<>(adminPageListPO.getPageNum(), adminPageListPO.getPageSize());
        Page<SceneEntity> pageResult = sceneMapper.sceneList(page, query);
        List<SceneEntity> sceneEntityList = pageResult.getRecords();
        List<SceneAllVO> sceneVOList = copyList2Scene(sceneEntityList);
        Map<String, Object> map = new HashMap<>();
        map.put("sceneList", sceneVOList);
        map.put("total", pageResult.getTotal());
        return Result.success(map, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result uploadImage(MultipartFile file) {
        String uploadUrl = upload(file,  OSSTypeConstant.SCENE_AVATAR_PATH);
        if (uploadUrl == null){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(uploadUrl,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result save(AdminSceneSavePO adminSceneSavePO) {
        if (Objects.isNull(adminSceneSavePO)|| Objects.isNull(adminSceneSavePO.getSceneGoods())){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        SceneEntity sceneEntity = new SceneEntity();

        //detail
        String detail = null;
        if (!Objects.isNull(adminSceneSavePO.getSceneDetail())){
            detail = String.join(",", adminSceneSavePO.getSceneDetail());
        }
        //goods
        List<SceneGoods> sceneGoods = adminSceneSavePO.getSceneGoods();
        List<String> goodsIdList = new ArrayList<>();
        for (SceneGoods sceneGood : sceneGoods) {
            goodsIdList.add(sceneGood.getGoodsId());
        }
        String goodsId = String.join(",", goodsIdList);

        sceneEntity.setSceneId(adminSceneSavePO.getSceneId());
        sceneEntity.setSceneName(adminSceneSavePO.getSceneName());
        sceneEntity.setSceneDesc(adminSceneSavePO.getSceneDesc());
        sceneEntity.setSceneType(adminSceneSavePO.getSceneType());
        sceneEntity.setSceneAvatar(adminSceneSavePO.getSceneAvatar());
        sceneEntity.setSceneDetail(detail);
        sceneEntity.setSceneGoods(goodsId);

        if ( adminSceneSavePO.getSceneId() == null || adminSceneSavePO.getSceneId() == -1){
            //添加
            sceneEntity.setSceneId(IdUtil.getCurrentId());
            sceneMapper.insert(sceneEntity);
        }else {
            //修改
            sceneEntity.setUpdateTime(new Date());
            sceneMapper.updateById(sceneEntity);
        }
        return Result.success(null,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result delete(Long sceneId) {
        try {
            sceneMapper.deleteById(sceneId);
            return Result.success(null,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
    }

    @Override
    public Result uploadDetail(MultipartFile file) {
        String uploadUrl = upload(file,  OSSTypeConstant.SCENE_DETAIL_PATH);
        if (uploadUrl == null){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(uploadUrl,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /*******************************************************private***************************************************/

    private SceneAllVO copy(SceneEntity sceneEntity) {
        SceneAllVO sceneAllVO = new SceneAllVO();
        List<SceneGoods> sceneGoodsList = new ArrayList<>();

        //goods
        String[] goodsParts = sceneEntity.getSceneGoods().split(",");
        //goodsId
        List<Long> goodsList = new ArrayList<>();
        for (String goods : goodsParts) {
            try {
                goodsList.add(Long.valueOf(goods));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for (Long goodsId : goodsList) {
            SceneGoods sceneGoods = new SceneGoods();
            GoodsEntity goodsEntity = goodsMapper.selectById(goodsId);
            sceneGoods.setGoodsId(String.valueOf(goodsEntity.getGoodsId()));
            sceneGoods.setGoodsName(goodsEntity.getGoodsName());
            sceneGoods.setGoodsPrice(goodsEntity.getGoodsPrice());
            sceneGoodsList.add(sceneGoods);
        }
        //详情图片
        String[] goodsDetailParts = sceneEntity.getSceneDetail().split(",");
        List<String> goodsDetailList = new ArrayList<>();
        for (String goodsDetail : goodsDetailParts) {
            goodsDetailList.add(goodsDetail);
        }

        sceneAllVO.setSceneId(String.valueOf(sceneEntity.getSceneId()));
        sceneAllVO.setSceneName(sceneEntity.getSceneName());
        sceneAllVO.setSceneDesc(sceneEntity.getSceneDesc());
        sceneAllVO.setSceneGoods(sceneGoodsList);
        sceneAllVO.setSceneType(sceneEntity.getSceneType());
        sceneAllVO.setSceneAvatar(sceneEntity.getSceneAvatar());
        sceneAllVO.setSceneDetail(goodsDetailList);
        sceneAllVO.setCreateTime(sceneEntity.getCreateTime());
        sceneAllVO.setUpdateTime(sceneEntity.getUpdateTime());


        return sceneAllVO;
    }


    private List<SceneAllVO> copyList2Scene(List<SceneEntity> sceneEntityList) {
        List<SceneAllVO> sceneAllVOList = new ArrayList<>();
        for (SceneEntity sceneEntity : sceneEntityList) {
            sceneAllVOList.add(copy(sceneEntity));
        }
        return sceneAllVOList;
    }

    private String upload(MultipartFile file, String typePath) {
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
}
