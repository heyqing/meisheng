package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

/**
 * ClassName:AdminChoiceAvatarSavePO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/3/10
 * @Author:Heyqing
 */
@Data
public class AdminChoiceAvatarSavePO {

    /**
     * 图片类型
     */
    private String type;

    /**
     * 图片url
     */
    private String imgUrl;
}
