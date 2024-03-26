package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.SaveBigTypePO;
import com.heyqing.shop_back.service.admin.AdminBigTypeService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:AdminBigTypeController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/2/13
 * @Author:Heyqing
 */
@RestController
@RequestMapping("admin/bigType")
public class AdminBigTypeController {
    @Autowired
    private AdminBigTypeService adminBigTypeService;

    /**
     * 查询商品大类
     *
     * @param adminPageListPO
     * @return
     */
    @PostMapping("list")
    public Result getList(@RequestBody AdminPageListPO adminPageListPO) {
        return adminBigTypeService.getList(adminPageListPO);
    }

    /**
     * 查询所有大类-下拉框使用
     *
     * @return
     */
    @PostMapping("listAll")
    public Result listAll() {
        return adminBigTypeService.listAll();
    }

    /**
     * 查询大类信息
     *
     * @param sortId
     * @return
     */
    @GetMapping("/{id}")
    public Result getSortById(@PathVariable("id") Long sortId) {
        return adminBigTypeService.getSortById(sortId);
    }

    /**
     * 修改or保存
     *
     * @param savaBigTypePO
     * @return
     */
    @PostMapping("save")
    public Result saveBigType(@RequestBody SaveBigTypePO savaBigTypePO) {
        return adminBigTypeService.saveBigType(savaBigTypePO);
    }

    /**
     * 删除
     *
     * @param sortId
     * @return
     */
    @GetMapping("delete/{id}")
    public Result deleteBigType(@PathVariable("id") Long sortId) {
        return adminBigTypeService.deleteBigType(sortId);
    }

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadImage")
    public Result uploadImage(MultipartFile file) {
        return adminBigTypeService.uploadImage(file);
    }
}
