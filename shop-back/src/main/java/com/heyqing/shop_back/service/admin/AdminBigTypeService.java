package com.heyqing.shop_back.service.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.SaveBigTypePO;
import com.heyqing.shop_back.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:AdminBigTypeService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/2/13
 * @Author:Heyqing
 */
public interface AdminBigTypeService {

    /**
     * 查询商品表单
     *
     * @param adminPageListPO
     * @return
     */
    Result getList(AdminPageListPO adminPageListPO);

    /**
     * 查询大类信息
     *
     * @param sortId
     * @return
     */
    Result getSortById(Long sortId);

    /**
     * 修改or保存大类
     *
     * @param savaBigTypePO
     * @return
     */
    Result saveBigType(SaveBigTypePO savaBigTypePO);

    /**
     * 删除大类
     *
     * @param sortId
     * @return
     */
    Result deleteBigType(Long sortId);

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    Result uploadImage(MultipartFile file);



    /**
     * 查询所有大类
     *
     * @return
     */
    Result listAll();

}
