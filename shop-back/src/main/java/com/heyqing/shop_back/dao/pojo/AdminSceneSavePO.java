package com.heyqing.shop_back.dao.pojo;

import com.heyqing.shop_back.vo.SceneGoods;
import lombok.Data;

import java.util.List;

/**
 * ClassName:AdminSceneSavePO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/3/5
 * @Author:Heyqing
 */
@Data
public class AdminSceneSavePO {

    /**
     * 场景管id
     */
    private Long sceneId;

    /**
     * 场景管图片
     */
    private String sceneAvatar;

    /**
     * 场景管名称
     */
    private String sceneName;

    /**
     * 场景管描述
     */
    private String sceneDesc;

    /**
     * 场景管类型
     */
    private String sceneType;

    /**
     * 场景管商品
     */
    private List<SceneGoods> sceneGoods;

    /**
     * 场景管详情
     */
    private List<String> sceneDetail;
}
