package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AdminChoiceAvatarSavePO;
import com.heyqing.shop_back.service.admin.AdminChoiceAvatarService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:AdminChoiceAvatarController
 * Package:com.heyqing.shop_back.controller.admin
 * Description:
 *
 * @Date:2024/3/10
 * @Author:Heyqing
 */
@RestController
@RequestMapping("admin/choice/avatar")
public class AdminChoiceAvatarController {

    @Autowired
    private AdminChoiceAvatarService adminChoiceAvatarService;

    /**
     * 获取图片url
     *
     * @return
     */
    @GetMapping("url")
    public Result getUrl() {
        return adminChoiceAvatarService.getUrl();
    }

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("uploadImage")
    public Result uploadImage(MultipartFile file) {
        return adminChoiceAvatarService.uploadImage(file);
    }

    /**
     * 保存
     *
     * @param adminChoiceAvatarSavePO
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody AdminChoiceAvatarSavePO adminChoiceAvatarSavePO) {
        return adminChoiceAvatarService.save(adminChoiceAvatarSavePO);
    }
}
