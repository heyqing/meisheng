package com.heyqing.shop_back.service.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.vo.Result;

/**
 * ClassName:AdminChoiceService
 * Package:com.heyqing.shop_back.service.admin
 * Description:
 *
 * @Date:2024/3/10
 * @Author:Heyqing
 */
public interface AdminChoiceService {
    /**
     * 获取列表
     *
     * @param adminPageListPO
     * @return
     */
    Result getList(AdminPageListPO adminPageListPO);

    /**
     * 删除
     *
     * @param choiceId
     * @return
     */
    Result delete(Long choiceId);
}
