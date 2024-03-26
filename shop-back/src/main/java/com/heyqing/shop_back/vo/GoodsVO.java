package com.heyqing.shop_back.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName:GoodsVO
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/2/26
 * @Author:Heyqing
 */
@Data
public class GoodsVO {
    /**
     * 商品id
     */
    private String  goodsId;
    /**
     * 商品小类名称
     */
    private String categoryName;
    /**
     * 商品类型
     */
    private String goodsType;
    /**
     * 商品视频
     */
    private String goodsVideo;
    /**
     * 商品图片
     */
    private String goodsAvatar;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品标题
     */
    private String goodsTitle;
    /**
     * 商品描述
     */
    private String goodsDesc;
    /**
     * 商品详情图片
     */
    private List<String> goodsPhoto;

    /**
     * 商品详情参数（图片）
     */
    private List<String> goodsParam;
    /**
     * 商品详情更多（图片）
     */
    private List<String> goodsDetail;
    /**
     * 商品大类名称
     */
    private String sortName;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
