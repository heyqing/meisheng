package com.heyqing.shop_back.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.heyqing.shop_back.dao.pojo.SceneSavePO;
import com.heyqing.shop_back.service.SceneService;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:SceneController
 * Package:com.heyqing.shop_back.controller
 * Description:
 *
 * @Date:2024/2/28
 * @Author:Heyqing
 */
@RestController
@RequestMapping("scene")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    /**
     * 获取轮播图数据
     *
     * @return
     */
    @GetMapping("swiperdata")
    public Result getSwiper() {
        return sceneService.getSwiper();
    }

    /**
     * 获取楼层数据
     *
     * @return
     */
    @GetMapping("floor")
    public Result getFloor() {
        return sceneService.getFloor();
    }

    /**
     * 查询场景详情
     *
     * @param sceneId
     * @return
     */
    @GetMapping("detail/{id}")
    public Result getDetail(@PathVariable("id") Long sceneId) {
        return sceneService.getDetail(sceneId);
    }

    /**
     * 获取定制图片
     *
     * @return
     */
    @GetMapping("custom")
    public Result getCustom() {
        return sceneService.getCustom();
    }

    /**
     * 存储客户定制
     *
     * @param sceneSavePO
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SceneSavePO sceneSavePO) {
        return sceneService.save(sceneSavePO);
    }
}
