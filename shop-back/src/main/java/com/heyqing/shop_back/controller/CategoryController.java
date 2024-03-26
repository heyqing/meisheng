package com.heyqing.shop_back.controller;

import com.heyqing.shop_back.dao.pojo.GetCategoryPO;
import com.heyqing.shop_back.service.CategoryService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:CategoryController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/1/29
 * @Author:Heyqing
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取商品大类
     *
     * @param getCategoryPO
     * @return
     */
    @PostMapping
    public Result getCategory(@RequestBody GetCategoryPO getCategoryPO) {
        return categoryService.getCategory(getCategoryPO);
    }
}
