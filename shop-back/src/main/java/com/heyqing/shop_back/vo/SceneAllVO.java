package com.heyqing.shop_back.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ClassName:SceneAllVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/3/2
 * @Author:Heyqing
 */
@Data
public class SceneAllVO {
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
     * 场景类型
     */
    private String sceneType;
    /**
     * 场景商品
     */
    private List<SceneGoods> sceneGoods;
    /**
     * 场景详情图片
     */
    private List<String> sceneDetail;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
