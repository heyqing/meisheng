package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.SaveSmallTypePO;
import com.heyqing.shop_back.service.admin.AdminSmallTypeService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:AdminSmallTypeController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/2/12
 * @Author:Heyqing
 */
@RestController
@RequestMapping("admin/smallType")
public class AdminSmallTypeController {

    @Autowired
    private AdminSmallTypeService adminSmallTypeService;

    /**
     * 查询小类
     *
     * @param adminPageListPO
     * @return
     */
    @PostMapping("list")
    public Result list(@RequestBody AdminPageListPO adminPageListPO) {
        return adminSmallTypeService.getSmallList(adminPageListPO);
    }

    /**
     * 查询所有小类
     *
     * @return
     */
    @PostMapping("listAll")
    public Result listAll() {
        return adminSmallTypeService.listAll();
    }

    /**
     * 添加小类
     *
     * @param saveSmallTypePO
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SaveSmallTypePO saveSmallTypePO) {
        return adminSmallTypeService.saveSmallType(saveSmallTypePO);
    }

    /**
     * 修改图片
     *
     * @param file
     * @return
     */
    @PostMapping("uploadImage")
    public Result uploadImage(MultipartFile file) {
        return adminSmallTypeService.uploadImage(file);
    }

    /**
     * 删除smallType
     *
     * @param categoryId
     * @return
     */
    @GetMapping("delete/{id}")
    public Result deleteSmallType(@PathVariable("id") Long categoryId) {
        return adminSmallTypeService.deleteSmallType(categoryId);
    }

}
