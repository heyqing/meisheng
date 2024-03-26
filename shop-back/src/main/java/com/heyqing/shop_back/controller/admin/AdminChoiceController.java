package com.heyqing.shop_back.controller.admin;

import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.service.admin.AdminChoiceService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:AdminChoiceController
 * Package:com.heyqing.shop_back.controller.admin
 * Description:
 *
 * @Date:2024/3/10
 * @Author:Heyqing
 */
@RestController
@RequestMapping("admin/choice")
public class AdminChoiceController {

    @Autowired
    private AdminChoiceService adminChoiceService;

    /**
     * 获取列表
     *
     * @param adminPageListPO
     * @return
     */
    @PostMapping("list")
    public Result getList(@RequestBody AdminPageListPO adminPageListPO) {
        return adminChoiceService.getList(adminPageListPO);
    }

    /**
     * 删除
     *
     * @param choiceId
     * @return
     */
    @GetMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long choiceId) {
        return adminChoiceService.delete(choiceId);
    }

}
