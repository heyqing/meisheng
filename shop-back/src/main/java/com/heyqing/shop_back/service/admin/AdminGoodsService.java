package com.heyqing.shop_back.service.admin;

import com.heyqing.shop_back.dao.pojo.*;
import com.heyqing.shop_back.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName:AdminGoodsService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/2/7
 * @Author:Heyqing
 */
public interface AdminGoodsService {

    /**
     * 获取商品列表
     *
     * @param adminPageListPO
     * @return
     */
    Result getList(AdminPageListPO adminPageListPO);

    /**
     * avatar上传
     *
     * @param file
     * @return
     */
    Result uploadImage(MultipartFile file);

    /**
     * 视频上传
     *
     * @param file
     * @return
     */
    Result uploadVideo(MultipartFile file);

    /**
     * 轮播图上传
     *
     * @param file
     * @return
     */
    Result uploadSwiper(MultipartFile file);

    /**
     * 商品详情
     *
     * @param file
     * @return
     */
    Result uploadDetail(MultipartFile file);

    /**
     * 商品参数
     *
     * @param file
     * @return
     */
    Result uploadParam(MultipartFile file);

    /**
     * 保存
     *
     * @param goodsSavaPO
     * @return
     */
    Result save(GoodsSavaPO goodsSavaPO);

    /**
     * 删除商品
     *
     * @param goodsId
     * @return
     */
    Result delete(Long goodsId);


    /**
     * 查询商品
     *
     * @param goodsSearchPO
     * @return
     */
    Result goods(GoodsSearchPO goodsSearchPO);
}
