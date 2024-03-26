package com.heyqing.shop_back.service.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.SaveSmallTypePO;
import com.heyqing.shop_back.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:AdminSmallTypeService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/2/13
 * @Author:Heyqing
 */
public interface AdminSmallTypeService {

    /**
     * 查询商品小类
     *
     * @param adminPageListPO
     * @return
     */
    Result getSmallList(AdminPageListPO adminPageListPO);

    /**
     * 添加小类
     *
     * @param saveSmallTypePO
     * @return
     */
    Result saveSmallType(SaveSmallTypePO saveSmallTypePO);

    /**
     * 商品小类更换图片
     *
     * @param file
     * @return
     */
    Result uploadImage(MultipartFile file);

    /**
     * 删除smallType
     *
     * @param categoryId
     * @return
     */
    Result deleteSmallType(Long categoryId);

    /**
     * 查询所有小类
     *
     * @return
     */
    Result listAll();
}
