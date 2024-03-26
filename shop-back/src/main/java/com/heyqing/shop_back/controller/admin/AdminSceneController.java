package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.AdminSceneSavePO;
import com.heyqing.shop_back.service.admin.AdminSceneService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:AdminSceneController
 * Package:com.heyqing.shop_back.controller.admin
 * Description:
 *
 * @Date:2024/3/2
 * @Author:Heyqing
 */
@RestController
@RequestMapping("admin/scene")
public class AdminSceneController {

    @Autowired
    private AdminSceneService adminSceneService;

    /**
     * 获取list
     *
     * @param adminPageListPO
     * @return
     */
    @PostMapping("list")
    public Result getList(@RequestBody AdminPageListPO adminPageListPO) {
        return adminSceneService.getList(adminPageListPO);
    }

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("uploadImage")
    public Result uploadImage(MultipartFile file) {
        return adminSceneService.uploadImage(file);
    }

    /**
     * 场景管详情
     *
     * @param file
     * @return
     */
    @PostMapping("uploadDetail")
    public Result uploadDetail(MultipartFile file) {
        return adminSceneService.uploadDetail(file);
    }

    /**
     * 保存场景管
     *
     * @param adminSceneSavePO
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody AdminSceneSavePO adminSceneSavePO) {
        return adminSceneService.save(adminSceneSavePO);
    }

    /**
     * 删除
     *
     * @param sceneId
     * @return
     */
    @GetMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long sceneId) {
        return adminSceneService.delete(sceneId);
    }

}
