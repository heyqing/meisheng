package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.GoodsSavaPO;
import com.heyqing.shop_back.dao.pojo.GoodsSearchPO;
import com.heyqing.shop_back.service.admin.AdminGoodsService;

import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * ClassName:AdminGoodsController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/2/7
 * @Author:Heyqing
 */
@RestController
@RequestMapping("admin/product")
public class AdminGoodsController {

    @Autowired
    private AdminGoodsService adminGoodsService;

    /**
     * 获取商品列表
     *
     * @param adminPageListPO
     * @return
     */
    @PostMapping("list")
    public Result getList(@RequestBody AdminPageListPO adminPageListPO) {
        return adminGoodsService.getList(adminPageListPO);
    }

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("uploadImage")
    public Result uploadImage(MultipartFile file) {
        return adminGoodsService.uploadImage(file);
    }

    /**
     * 视频上传
     *
     * @param file
     * @return
     */
    @PostMapping("uploadVideo")
    public Result uploadVideo(MultipartFile file) {
        return adminGoodsService.uploadVideo(file);
    }

    /**
     * 轮播图上传
     *
     * @param file
     * @return
     */
    @PostMapping("uploadSwiper")
    public Result uploadSwiper(MultipartFile file) {
        return adminGoodsService.uploadSwiper(file);
    }

    /**
     * 商品详情
     *
     * @param file
     * @return
     */
    @PostMapping("uploadDetail")
    public Result uploadDetail(MultipartFile file) {
        return adminGoodsService.uploadDetail(file);
    }

    /**
     * 商品参数
     *
     * @param file
     * @return
     */
    @PostMapping("uploadParam")
    public Result uploadParam(MultipartFile file) {
        return adminGoodsService.uploadParam(file);
    }

    /**
     * 保存
     *
     * @param goodsSavaPO
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody GoodsSavaPO goodsSavaPO) {
        return adminGoodsService.save(goodsSavaPO);
    }

    /**
     * 删除商品
     *
     * @param goodsId
     * @return
     */
    @GetMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long goodsId) {
        return adminGoodsService.delete(goodsId);
    }

    /**
     * 查询商品
     *
     * @param goodsSearchPO
     * @return
     */
    @PostMapping("goods")
    public Result goods(@RequestBody GoodsSearchPO goodsSearchPO) {
        return adminGoodsService.goods(goodsSearchPO);
    }
}
