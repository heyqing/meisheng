package com.heyqing.shop_back.service;

import com.heyqing.shop_back.dao.pojo.SceneSavePO;
import com.heyqing.shop_back.vo.Result;

/**
 * ClassName:SceneService
 * Package:com.heyqing.shop_back.service
 * Description:
 *
 * @Date:2024/2/28
 * @Author:Heyqing
 */
public interface SceneService {
    /**
     * 获取轮播图
     *
     * @return
     */
    Result getSwiper();

    /**
     * 获取楼层
     *
     * @return
     */
    Result getFloor();

    /**
     * 获取详情
     *
     * @param sceneId
     * @return
     */
    Result getDetail(Long sceneId);

    /**
     * 获取定制图片
     *
     * @return
     */
    Result getCustom();

    /**
     * 存储客户定制
     *
     * @param sceneSavePO
     * @return
     */
    Result save(SceneSavePO sceneSavePO);
}
