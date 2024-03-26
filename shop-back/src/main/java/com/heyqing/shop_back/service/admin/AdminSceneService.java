package com.heyqing.shop_back.service.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.AdminSceneSavePO;
import com.heyqing.shop_back.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:AdminSceneService
 * Package:com.heyqing.shop_back.service.admin
 * Description:
 *
 * @Date:2024/3/2
 * @Author:Heyqing
 */
public interface AdminSceneService {
    /**
     * 获取list
     *
     * @param adminPageListPO
     * @return
     */
    Result getList(AdminPageListPO adminPageListPO);

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    Result uploadImage(MultipartFile file);

    /**
     * 保存场景管
     *
     * @param adminSceneSavePO
     * @return
     */
    Result save(AdminSceneSavePO adminSceneSavePO);

    /**
     * 删除
     *
     * @param sceneId
     * @return
     */
    Result delete(Long sceneId);

    /**
     * 场景管详情
     *
     * @param file
     * @return
     */
    Result uploadDetail(MultipartFile file);
}
