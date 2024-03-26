package com.heyqing.shop_back.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName:GetDetailVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/1/30
 * @Author:Heyqing
 */
@Data
public class GetDetailVO {
    /**
     * 商品id
     */
    private String  goodsId;
    /**
     * 商品avatar
     */
    private String goodsAvatar;
    /**
     * 商品详情视频
     */
    private String goodsVideo;
    /**
     * 商品详情图片
     */
    private List<String> goodsPhoto;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品描述
     */
    private String goodsDesc;
    /**
     * 商品详情参数（图片）
     */
    private List<String> goodsParam;
    /**
     * 商品详情更多（图片）
     */
    private List<String> goodsDetail;

}
