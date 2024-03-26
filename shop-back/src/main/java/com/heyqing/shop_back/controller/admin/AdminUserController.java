package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AddAdminPO;
import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.AuthAdminPO;
import com.heyqing.shop_back.dao.pojo.ModifyPasswordPO;
import com.heyqing.shop_back.service.admin.AdminService;
import com.heyqing.shop_back.service.UserService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:AdminUserController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/2/5
 * @Author:Heyqing
 */
@RestController
@RequestMapping("admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    /**
     * 查询用户
     *
     * @param adminPageListPO
     * @return
     */
    @PostMapping("list")
    public Result getUserList(@RequestBody AdminPageListPO adminPageListPO) {
        return userService.getUserList(adminPageListPO);
    }

    /**
     * 用户修改密码
     *
     * @param modifyPasswordPO
     * @return
     */
    @PostMapping("modifyPassword")
    public Result modifyPassword(@RequestBody ModifyPasswordPO modifyPasswordPO) {
        return adminService.modifyPassword(modifyPasswordPO);
    }

    /**
     * 管理员身份验证
     *
     * @param authAdminPO
     * @return
     */
    @PostMapping("adminAuth")
    public Result adminAuth(@RequestBody AuthAdminPO authAdminPO) {
        return adminService.adminAuth(authAdminPO);
    }

    /**
     * 获取admin
     *
     * @param adminPageListPO
     * @return
     */
    @PostMapping("adminList")
    public Result adminList(@RequestBody AdminPageListPO adminPageListPO) {
        return adminService.getList(adminPageListPO);
    }

    /**
     * 添加admin
     *
     * @param addAdminPO
     * @return
     */
    @PostMapping("addAdmin")
    public Result addAdmin(@RequestBody AddAdminPO addAdminPO) {
        return adminService.addAdmin(addAdminPO);
    }

    /**
     * 删除admin
     *
     * @param adminId
     * @return
     */
    @GetMapping("delete/{id}")
    public Result deleteAdmin(@PathVariable("id") Long adminId) {
        return adminService.deleteAdmin(adminId);
    }
}
