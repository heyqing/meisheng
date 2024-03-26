package com.heyqing.shop_back.service.admin;

import com.heyqing.shop_back.dao.pojo.*;
import com.heyqing.shop_back.vo.Result;

/**
 * ClassName:AdminService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
public interface AdminService {
    /**
     * 管理员登录
     *
     * @param adminLoginPO
     * @return
     */
    Result login(AdminLoginPO adminLoginPO);

    /**
     * 修改密码
     *
     * @param modifyPasswordPO
     * @return
     */
    Result modifyPassword(ModifyPasswordPO modifyPasswordPO);

    /**
     * 管理员身份验证
     *
     * @param authAdminPO
     * @return
     */
    Result adminAuth(AuthAdminPO authAdminPO);

    /**
     * 获取admin
     *
     * @param adminPageListPO
     * @return
     */
    Result getList(AdminPageListPO adminPageListPO);

    /**
     * 添加管理员
     *
     * @param addAdminPO
     * @return
     */
    Result addAdmin(AddAdminPO addAdminPO);

    /**
     * 删除admin
     *
     * @param adminId
     * @return
     */
    Result deleteAdmin(Long adminId);
}
