package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName:GoodsSavaPO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/27
 * @Author:Heyqing
 */
@Data
public class GoodsSavaPO {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品图片
     */
    private String goodsAvatar;
    /**
     * 商品描述
     */
    private String goodsDesc;
    /**
     * 商品类型
     */
    private String goodsType;
    /**
     * 商品标签
     */
    private String goodsTitle;
    /**
     * 商品视频
     */
    private String goodsVideo;
    /**
     * 商品轮播图
     */
    private List<String> goodsPhoto;
    /**
     * 商品详情
     */
    private List<String> goodsDetail;
    /**
     * 商品参数
     */
    private List<String> goodsParam;
    /**
     * 商品大类
     */
    private String sortName;
    /**
     * 商品小类
     */
    private String categoryName;

}
