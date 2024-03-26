package com.heyqing.shop_back.service.admin;

import com.heyqing.shop_back.dao.pojo.AdminChoiceAvatarSavePO;
import com.heyqing.shop_back.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:AdminChoiceAvatarService
 * Package:com.heyqing.shop_back.service.admin
 * Description:
 *
 * @Date:2024/3/10
 * @Author:Heyqing
 */
public interface AdminChoiceAvatarService {

    /**
     * 获取图片url
     *
     * @return
     */
    Result getUrl();

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    Result uploadImage(MultipartFile file);

    /**
     * 保存
     *
     * @param adminChoiceAvatarSavePO
     * @return
     */
    Result save(AdminChoiceAvatarSavePO adminChoiceAvatarSavePO);
}
