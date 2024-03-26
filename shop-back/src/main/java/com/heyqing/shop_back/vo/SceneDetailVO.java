package com.heyqing.shop_back.vo;

import lombok.Data;

import java.util.List;

/**
 * ClassName:SceneDetailVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/2/28
 * @Author:Heyqing
 */
@Data
public class SceneDetailVO {
    /**
     * 场景id
     */
    private String sceneId;
    /**
     * 场景图片
     */
    private String sceneAvatar;
    /**
     * 场景名称
     */
    private String sceneName;
    /**
     * 场景描述
     */
    private String sceneDesc;
    /**
     * 场景商品
     */
    private List<GetFloorVO> sceneGoods;
    /**
     * 场景详情图片
     */
    private List<String> sceneDetail;
}
